package com.zhcet.zhcetnavigationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class List_Teachers extends AppCompatActivity {
    TextView SU,NA,AMA,MMSB,MRW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__teachers);
       /* SU=(TextView)findViewById(R.id.SU);
        NA=(TextView)findViewById(R.id.NA);
        AMA=(TextView)findViewById(R.id.AMA);
        MMSB=(TextView)findViewById(R.id.MMSB);
        MRW=(TextView)findViewById(R.id.MRW);

        SU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Prof Sarosh Umar is selected",Toast.LENGTH_SHORT).show();
                //is track me or update clicked?
                //in case of don't track me, do something ......
                Intent gotomap=new Intent();
                gotomap.setClass(List_Teachers.this,LocationActivity.class);
                startActivity(gotomap);
            }
        });

        NA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Prof Nesar Ahmad is selected",Toast.LENGTH_SHORT).show();
            }
        });

        AMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Prof Ash Mohd Abbas is selected",Toast.LENGTH_SHORT).show();
            }
        });
*/
    }
}
