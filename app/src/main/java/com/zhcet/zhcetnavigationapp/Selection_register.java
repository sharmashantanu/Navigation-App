package com.zhcet.zhcetnavigationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selection_register extends AppCompatActivity {


    Button register,alreadyreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_register);
        register=(Button)findViewById(R.id.register);
        alreadyreg=(Button)findViewById(R.id.alregister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologin_teacher = new Intent();
                gotologin_teacher.setClass(Selection_register.this,Login_teacher.class);
                startActivity(gotologin_teacher);
            }
        });

        alreadyreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologin=new Intent();
                gotologin.setClass(Selection_register.this,Login.class);
                startActivity(gotologin);
            }
        });

    }


}
