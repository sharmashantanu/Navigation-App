package com.zhcet.zhcetnavigationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ubtn,tchbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ubtn=(Button)findViewById(R.id.student);
        tchbtn=(Button)findViewById(R.id.teacher);

        tchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodept = new Intent();
                gotodept.setClass(getApplicationContext(),SignUp.class);
                startActivity(gotodept);
            }
        });

        ubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodept=new Intent();
                gotodept.setClass(MainActivity.this,MainDepartments.class);
                startActivity(gotodept);
            }
        });
    }

}
