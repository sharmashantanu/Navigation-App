package com.zhcet.zhcetnavigationapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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

public class GpsTeacherTracking extends Activity {

    RadioButton btnShowLocation,btnnotShow;

    // GPSTracker class
    GPSTracker gps;
    CustomAdapter cp;

    ListView simpleList;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mfirebase,sfirebase;
    double latitude;
    double longitude;

    //static String status="Not Active";
    String namer="";
    String email1="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_teacher_tracking);
        mfirebase= FirebaseDatabase.getInstance().getReference().child("Users").child("Register");
        btnShowLocation = (RadioButton) findViewById(R.id.btnShowLocation);
        btnnotShow = (RadioButton) findViewById(R.id.dbtn);
         mAuth=FirebaseAuth.getInstance();
        //FirebaseUser user = mAuth.getCurrentUser();
       // String userID = user.getUid();

        FirebaseUser user = mAuth.getCurrentUser();
        email1=user.getEmail();
        email1=email1.replace('.',',');
        Toast.makeText(getApplicationContext(),"ye`ee"+email1,Toast.LENGTH_SHORT).show();
        mfirebase=mfirebase.child(email1);
        sfirebase=mfirebase.child("name");
        // show location button click event

          btnnotShow.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                 // setstatus="";
                 // cp.notifyDataSetChanged();
               /*   TeacherStatus ts=new TeacherStatus("Not Active");
                  ts.setStatus("Not Active");*/



              }
          });
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //status="Active Now";
            //  cp.notifyDataSetChanged();
                TeacherStatus ts=new TeacherStatus("Not Active");
                ts.setStatus("Active Now");
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

         Log.v("hello","lat:"+latitude);
         Log.v("hello","longggg"+longitude);
         HashMap<String,Object> coordi=new HashMap<>();
         coordi.put("latitude",String .valueOf(latitude));
         coordi.put("longitude",String.valueOf(longitude));
         coordi.put("NewStatus","true");
         mfirebase.updateChildren(coordi);

         final UserInformation use=new UserInformation();
         use.setEmail(email1);
        // use.setName();
         use.setLongitude(String.valueOf(longitude));
         use.setLatitude(String.valueOf(latitude));
         use.setStatus(true);
        // coordi.get("name");

         Coordinate co=new Coordinate();

         Toast.makeText(getApplicationContext(),use.getLatitude(),Toast.LENGTH_SHORT).show();
         Toast.makeText(getApplicationContext(),co.getLatitude(),Toast.LENGTH_SHORT).show();

         mfirebase.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 namer = dataSnapshot.child("name").getValue(String.class);

                Toast.makeText(getApplicationContext(),"current name:"+namer,Toast.LENGTH_SHORT).show();

                 Log.v("hello","namer"+namer);
                 use.setName(namer);
                 Log.v("he",use.getName()+use.getEmail()+" "+use.getLatitude()+" "+use.getLongitude());

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
