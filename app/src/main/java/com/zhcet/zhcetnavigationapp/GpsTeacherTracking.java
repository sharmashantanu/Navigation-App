package com.zhcet.zhcetnavigationapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GpsTeacherTracking extends Activity {

    RadioButton btnShowLocation;

    // GPSTracker class
    GPSTracker gps;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mfirebase,sfirebase;
    double latitude;
    double longitude;
    String namer="";
    String email1="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_teacher_tracking);
        mfirebase= FirebaseDatabase.getInstance().getReference().child("Users").child("Register");
        btnShowLocation = (RadioButton) findViewById(R.id.btnShowLocation);
         mAuth=FirebaseAuth.getInstance();
        //FirebaseUser user = mAuth.getCurrentUser();
       // String userID = user.getUid();

        FirebaseUser user = mAuth.getCurrentUser();
        email1=user.getEmail();
        email1=email1.replace('.',',');
        //Toast.makeText(getApplicationContext(),"ye`ee"+email1,Toast.LENGTH_SHORT).show();
        mfirebase=mfirebase.child(email1);
        sfirebase=mfirebase.child("name");
        // show location button click event
       /* mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                   // Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                   // toastMessage("Successfully signed in with: " + user.getEmail());
                    email=user.getEmail();
                    email=email.replace('.',',');
                    Toast.makeText(getApplicationContext(),"ye dekh:"+email,Toast.LENGTH_SHORT);

                } else {
                    // User is signed out
                  //  Log.d(TAG, "onAuthStateChanged:signed_out");
                   // toastMessage("Successfully signed out.");
                }
                // ...
            }
        };*/
/*
        mfirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

/*

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    String uid = user.getUid();
                    Toast.makeText(getApplicationContext(),"id="+uid,Toast.LENGTH_SHORT).show();
                    String password = user.getDisplayName();
                    String email = user.getEmail();
                    Email=email;
                    Log.w("heyy","correct hai bhai ..................");
                    mfirebase=mfirebase.child(Decode(email));
                    mfirebase=mfirebase.child("name");
                    DataSnapshot dataSnapshot = null;
                    if(dataSnapshot.hasChild("name")){
                        String namer=dataSnapshot.getValue(String.class);
                        Toast.makeText(getApplicationContext(),"namer:"+namer,Toast.LENGTH_SHORT).show();
                    }

                    mfirebase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String name = dataSnapshot.getValue(String.class);
                            Name=name;
                            Toast.makeText(getApplicationContext(),"got it:"+name,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    Toast.makeText(getApplicationContext(),"email:"+email+password,Toast.LENGTH_SHORT).show();
                    // Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(getApplicationContext(),"Successfully authentized",Toast.LENGTH_SHORT).show();

                } else {
                    // User is signed out
                    // Log.d(TAG, "onAuthStateChanged:signed_out");
                    // toastMessage("Successfully signed out.");
                }
                // ...
            }
        };*/
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(GpsTeacherTracking.this);
                // check if GPS enabled
                if(gps.canGetLocation()){

                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    updatecoord();


                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });
    }
    public String Decode(String name){

        return name.replace(".",",");
    }

     public void updatecoord(){

         HashMap<String,Object> coordi=new HashMap<>();
         coordi.put("latitude",String .valueOf(latitude));
         coordi.put("longitude",String.valueOf(longitude));
         mfirebase.updateChildren(coordi) ;

         final UserInformation use=new UserInformation();
         use.setEmail(email1);
        // use.setName();
         use.setLongitude(String.valueOf(longitude));
         use.setLatitude(String.valueOf(latitude));
        // coordi.get("name");


         mfirebase.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 namer = dataSnapshot.child("name").getValue(String.class);
                Toast.makeText(getApplicationContext(),"current name:"+namer,Toast.LENGTH_SHORT).show();
                 //do what you want with the email
                 Log.v("hello","namer"+namer);
                 use.setName(namer);
                 Log.v("he",use.getName()+use.getEmail()+use.getLatitude()+use.getLongitude());
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });

         // use.setName(namer);
          //Toast.makeText(getApplicationContext(),"emailL:"+use.getEmail()+"name:"+use.getName()+use.getLongitude()+use.getLatitude(),Toast.LENGTH_LONG).show();

         // coordi.

     }


}


/*
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
          /*  UserInformation uInfo = new UserInformation();
           uInfo.setName(ds.child(email).getValue(UserInformation.class).getName()); //set the name
            uInfo.setEmail(ds.child(email).getValue(UserInformation.class).getEmail()); //set the email*/
/*
            Coordinate cr=new Coordinate();
            cr.setName(ds.child(email).getValue(Coordinate.class).getName());
            cr.setLatitude(ds.child(email).getValue(Coordinate.class).getLatitude());
            cr.setLongitude(ds.child(email).getValue(Coordinate.class).getLongitude());
/*
            Log.d("hello",cr.getName());
            Log.d("hello1",cr.getLatitude());
            Log.d("helo",cr.getLongitude());*/

//display all the information
           /* Log.d(TAG, "showData: name: " + uInfo.getName());
            Log.d(TAG, "showData: email: " + uInfo.getEmail());
            Log.d(TAG, "showData: phone_num: " + uInfo.getPhone_num());

            ArrayList<String> array  = new ArrayList<>();
            array.add(uInfo.getName());
            array.add(uInfo.getEmail());
            array.add(uInfo.getPhone_num());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);
        }
    }*/
