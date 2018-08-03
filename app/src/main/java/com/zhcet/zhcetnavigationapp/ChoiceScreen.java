package com.zhcet.zhcetnavigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Shantanu on 18-11-2017.
 */

public class ChoiceScreen extends AppCompatActivity {
    private static final String TAG = "Choice";

    Button btn_student;
    Button btn_teacher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_screen);

        btn_student = (Button) findViewById(R.id.btn_student);
        btn_teacher = (Button) findViewById(R.id.btn_teacher);

        btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceScreen.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
