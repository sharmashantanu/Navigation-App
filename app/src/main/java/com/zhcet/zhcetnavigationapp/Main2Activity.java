package com.zhcet.zhcetnavigationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    Button csbtn,elebtn,ecebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        csbtn=(Button)findViewById(R.id.csbtn);
        elebtn=(Button)findViewById(R.id.elebtn);
        ecebtn=(Button)findViewById(R.id.ecebtn);

        csbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocslist=new Intent();
                gotocslist.setClass(Main2Activity.this,NavigationActivity.class);
                startActivity(gotocslist);
            }
        });

        ecebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheck=new Intent();
                gotocheck.setClass(getApplicationContext(),List_electronics.class);
                startActivity(gotocheck);
            }
        });
    }
}
