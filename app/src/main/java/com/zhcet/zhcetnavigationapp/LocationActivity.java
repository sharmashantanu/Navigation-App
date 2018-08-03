package com.zhcet.zhcetnavigationapp;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

import static android.icu.lang.UCharacter.getDirection;
import static com.zhcet.zhcetnavigationapp.R.drawable.zhcet;

public class LocationActivity extends FragmentActivity implements
        OnMapReadyCallback ,
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener {

    int PROXIMITY_RADIUS = 10000;
    String uname="";
    private GoogleMap mMap;
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
    LocationManager locationManager;
    double end_latitude, end_longitude;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;


    double inilan;
    double inilong;
    LatLng latLng,initial_latlng;
    SupportMapFragment mFragment;
    Marker currLocationMarker;

    private DatabaseReference fire_reference,fire_ref2;

    private Context mContext;
    public boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;
    static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MIN_TIME_BW_UPDATES = 1;
    //FirebaseDatabase mFirebaseinstance;
    //DatabaseReference mDtabasereference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
       // fire_reference= FirebaseDatabase.getInstance().getReference();
       // fire_ref2 = FirebaseDatabase.getInstance().getReference().child("Users");

    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker at your choice place and move the camera
        try {
            String name=" ";

            Intent iin= getIntent();
            Bundle b = iin.getExtras();
            Coordinate model = (Coordinate) getIntent().getSerializableExtra("Editing");
            name=model.getName();
            inilan=Double.parseDouble(model.getLatitude());
            inilong=Double.parseDouble(model.getLongitude());

           /* if(b!=null){
                String out=b.getString("name");
                name=out;
            }*/

            Toast.makeText(getApplicationContext(),name+"is selected",Toast.LENGTH_SHORT).show();
            initial_latlng = new LatLng(inilan,inilong);

            mMap.addMarker(new MarkerOptions().position(initial_latlng).title(name));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(initial_latlng));
            mMap.setMapType(mMap.MAP_TYPE_NORMAL);
            //mMap = googleMap;
            //showingmap();
            mMap.setMyLocationEnabled(true);

            buildGoogleApiClient();

            mGoogleApiClient.connect();
          //  Toast.makeText(getApplicationContext(),"Build Done",Toast.LENGTH_LONG).show();
           // mMap.setOnMarkerClickListener(this);
        } catch (SecurityException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
           // Log.d("exception:",ex.toString());
            ex.printStackTrace();
        }
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(27.89, 78.088);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    protected synchronized void buildGoogleApiClient() {
      //  Toast.makeText(this, "build Google Api Client", Toast.LENGTH_LONG).show();
        mGoogleApiClient = new GoogleApiClient.Builder(LocationActivity.this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            Log.v("isGPSEnabled", "=" + isGPSEnabled);

            // getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            Log.v("isNetworkEnabled", "=" + isNetworkEnabled);

            if (isGPSEnabled == false && isNetworkEnabled == false) {
                // no network provider is enabled
                 Toast.makeText(getApplicationContext(),"Enable your gps service",Toast.LENGTH_SHORT).show();
            } else {
                this.canGetLocation = true;
                if (isNetworkEnabled) {
                    location = null;
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                           // showingmap();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    location = null;
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");

                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                            }
                        }
                    }
                }
            }
        } catch (SecurityException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
        }
        return location;
    }

    @Override
    public void onLocationChanged(Location location) {

        if (currLocationMarker != null) {

            currLocationMarker.remove();
        }
      //  all cabins locations ( using NearbyPlacesData)...
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        /*MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        Toast.makeText(getApplicationContext(),"now current location ",Toast.LENGTH_SHORT).show();
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        currLocationMarker = mMap.addMarker(markerOptions);
        Toast.makeText(getApplicationContext(),"marker added",Toast.LENGTH_SHORT).show();*/
        //showingmap();
        Toast.makeText(this, latLng.toString(), Toast.LENGTH_LONG).show();
       // Toast.makeText(this,"Location Changed",Toast.LENGTH_SHORT).show();

        //zoom to current position:
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(40).build();

        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

       // Toast.makeText(this, "onConnected", Toast.LENGTH_SHORT).show();
        Location mLastLocation = null;
        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            //Toast.makeText(this, "inside try after onConnected", Toast.LENGTH_SHORT).show();
        } catch (SecurityException ex) {
           // Toast.makeText(this, "inside catch any excp", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        if (mLastLocation != null) {
            //place marker at current position
            //mGoogleMap.clear();
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
          //  Toast.makeText(this, "is it working inside latlng", Toast.LENGTH_SHORT).show();
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            Toast.makeText(this, "current position is:", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, latLng.toString(), Toast.LENGTH_LONG).show();
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            currLocationMarker = mMap.addMarker(markerOptions);
            end_longitude=mLastLocation.getLongitude();
            end_latitude=mLastLocation.getLatitude();


           showingmap(end_latitude,end_longitude);
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); //5 seconds
        //Toast.makeText(this, "inside interval is set", Toast.LENGTH_SHORT).show();
        mLocationRequest.setFastestInterval(3000); //3 seconds
       // Toast.makeText(this, "inside fastest is set", Toast.LENGTH_SHORT).show();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

      /*  if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/
        //   LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) MapLocationActivity.this);
    }

    @Override
    public void onConnectionSuspended(int i) {

        Toast.makeText(this,"onConnectionSuspended",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(this,"onConnectionFailed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
       // end_latitude = marker.getPosition().latitude;//database .............
       // end_longitude =  marker.getPosition().longitude;//database...........
        return false;
    }

    public void showingmap(double lat,double lang)
    {
                Object dataTransfer[] ;
                dataTransfer = new Object[3];
                String url = getDirectionsUrl();
                GetDirectionsData getDirectionsData = new GetDirectionsData();
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;
                dataTransfer[2] = new LatLng(lat,lang);
                getDirectionsData.execute(dataTransfer);

       //fire_ref2=FirebaseDatabase.getInstance().getReference().child("Users").child("Coordinates");



/*
        Polyline line= mMap.addPolyline(new PolylineOptions()
        .add(new LatLng(27.9153659,78.0799854) , new LatLng(27.9157299,78.0797595))
                //.add()
        .width(10)
        .color(Color.BLUE)
        .geodesic(true));

       Polyline line2=mMap.addPolyline(new PolylineOptions()
       .add(new LatLng(27.9153659,78.0799854) , new LatLng(27.9154762,78.0799787))
       .color(Color.GREEN)
       .width(20)
         .geodesic(true)
       );*/

        Polyline line3= mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(inilan,inilong),new LatLng(end_latitude,end_longitude))
                .color(Color.GREEN)
                .width(10)
                .geodesic(true));

        Polyline p1=mMap.addPolyline(new PolylineOptions()
        .add(new LatLng(27.915574999999997, 78.07981166666667),
                new LatLng( 27.915676666666666,78.07980166666667),
                new LatLng(27.915733333333332,78.07976666666667),
                new LatLng(27.915745,78.07978 ),
                new LatLng( 27.91578833333333,78.07977666666667))
        .color(Color.CYAN)
                .width(5)
                .geodesic(true)
        );





    }

    private String getDirectionsUrl()
    {
        StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        googleDirectionsUrl.append("origin="+initial_latlng.latitude+","+initial_latlng.longitude);
        googleDirectionsUrl.append("&destination="+end_latitude+","+end_longitude);
        googleDirectionsUrl.append("&key="+"AIzaSyCAcfy-02UHSu2F6WeQ1rhQhkCr51eBL9g");

        return googleDirectionsUrl.toString();
    }

    private String getUrl(double latitude, double longitude, String nearbyPlace)
    {
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyBj-cnmMUY21M0vnIKz0k3tD3bRdyZea-Y");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }
}
