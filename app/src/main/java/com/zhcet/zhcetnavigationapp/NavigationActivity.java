package com.zhcet.zhcetnavigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.spec.ECField;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseReference mReference;


    Button first,sec,third,forth,warsi,izhar,athar,rashid,saiful,abdul,akhtar,inamullah,tameem,asad,muneeb,faisal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        first=(Button)findViewById(R.id.Button1);
         mReference=FirebaseDatabase.getInstance().getReference().child("Users").child("Register");
        sec=(Button)findViewById(R.id.Button2);
        third=(Button)findViewById(R.id.Button3);
        forth=(Button)findViewById(R.id.Button4);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //if(user.name=="hardcoded name")
                    Toast.makeText(getApplicationContext(), "Prof Sarosh Umar is selected", Toast.LENGTH_SHORT).show();
                    Coordinate cro=new Coordinate();
                    if(cro.getLongitude()==null){
                        Coordinate cr = new Coordinate("Sarosh Omar", "27.9157335", "78.0798319");
                        Intent gotomap=new Intent(NavigationActivity.this, LocationActivity.class);
                        gotomap.putExtra("Editing",cr);
                        startActivity(gotomap);

                    }
                    else{
                        Coordinate cr = new Coordinate();
                        Intent gotomap=new Intent(NavigationActivity.this, LocationActivity.class);
                        gotomap.putExtra("Editing",cr);
                        startActivity(gotomap);
                    }


                }catch (Exception ex){
                   // Log.d("exception",ex.toString());
                    ex.printStackTrace();
                }
            }
        });

        sec=(Button)findViewById(R.id.Button2);

        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Coordinate cr=new Coordinate("Nesar Ahmad","27.9158089","78.0797263");
                    Intent gotomap=new Intent(NavigationActivity.this,LocationActivity.class);
                    gotomap.putExtra("Editing",cr);
                    startActivity(gotomap);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
              //  Toast.makeText(getApplicationContext(),"selected",Toast.LENGTH_SHORT).show();


            }
        });

        third=(Button)findViewById(R.id.Button3);

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    // Toast.makeText(getApplicationContext(),"selected",Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("Ash Mohd. Abbas", "27.9157299", "78.0797595");
                    Intent gotomap = new Intent();
                    gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        forth=(Button)findViewById(R.id.Button4);

        forth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    // Toast.makeText(getApplicationContext(),"selected",Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("M. M. Sufyan Beg", "27.9152182", "78.0798490");
                    Intent gotomap = new Intent();
                    gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        warsi=(Button)findViewById(R.id.Button5);
        warsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("M.R.Warsi", "27.9159081", "78.0798145");
                    Intent gotomap = new Intent();
                    gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });


        izhar=(Button)findViewById(R.id.Button6);

        izhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("Dr.Izharuddin", "27.9155473", "78.0801416");
                    Intent gotomap = new Intent();
                    gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        /*athar=(Button)findViewById(R.id.Button7);
        athar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"selected",Toast.LENGTH_SHORT).show();
                Intent gotomap=new Intent();
                gotomap.setClass(NavigationActivity.this,LocationActivity.class);
                startActivity(gotomap);
                Coordinate cr=new Coordinate();
            }
        });*/

        rashid=(Button)findViewById(R.id.Button8);

        rashid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("Rashid Ali ", "27.9155952 ", "78.0800012 ");
                    Intent gotomap = new Intent();
                    gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });


        saiful=(Button)findViewById(R.id.Button9);

        saiful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("Saiful Islam ", "27.9154762 ", "78.0799787 ");
                    Intent gotomap = new Intent();
                    gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        });



        abdul=(Button)findViewById(R.id.Button10);

        abdul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("Abdul Qadeer ", "27.9153708 ", "78.0800533 ");
                    Intent gotomap = new Intent();
                    gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });



        akhtar=(Button)findViewById(R.id.Button11);

        akhtar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("Nadeem Akhtar", "27.9152466", "78.0799076");
                    Intent gotomap = new Intent();
                    gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });


      /*  inamullah=(Button)findViewById(R.id.Button12);

        inamullah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"selected",Toast.LENGTH_SHORT).show();
                Intent gotomap=new Intent();
                gotomap.setClass(NavigationActivity.this,LocationActivity.class);
                startActivity(gotomap);
                Coordinate cr=new Coordinate();
            }
        });*/


        tameem=(Button)findViewById(R.id.Button13);

        tameem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("Tameem Ahmed", "27.9153659", "78.0799854");
                    Intent gotomap = new Intent();
                    gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });



        asad=(Button)findViewById(R.id.Button14);

        asad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("Asad M Khan", "27.9143441", "78.0817736");
                    Intent gotomap = new Intent();
                    gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });


        muneeb=(Button)findViewById(R.id.Button15);

        muneeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("Muneeb Hasan Khan", "27.9145803", "78.0803474");
                    Intent gotomap = new Intent();
                    gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        });



        faisal=(Button)findViewById(R.id.Button16);

        faisal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_SHORT).show();
                Coordinate cr = new Coordinate();
                Intent gotomap = new Intent();
                gotomap.setClass(NavigationActivity.this, LocationActivity.class);
                gotomap.putExtra("Editing", cr);
                startActivity(gotomap);
            }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Action is not added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
