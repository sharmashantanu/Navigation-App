package com.zhcet.zhcetnavigationapp;


 import android.content.Intent;
 import android.os.Bundle;
 import android.support.annotation.Nullable;
 import android.support.v4.app.Fragment;
 import android.util.Log;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.AdapterView;
 import android.widget.ListView;
 import android.widget.TextView;
 import android.widget.Toast;

/**
 * Created by Shantanu on 02-04-2017.
 */

public class computer extends Fragment{
    View myview;
    ListView simpleList;
    //Context context;
    //CustomAdapter listAdapter;

    public static int [] teacherImages={R.drawable.sumar,R.drawable.nesarahmad,R.drawable.abbas,R.drawable.mrwarsi,R.drawable.izharuddin,R.drawable.rashidali,R.drawable.saifulislam,R.drawable.nadeemakhtar,R.drawable.abdulqadeer,R.drawable.tameemahmad,R.drawable.asadmohdkhan,R.drawable.muneebhasan,R.drawable.faisalalam,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu};
    public static String [] teacherNameList={"Prof. Mohammad Sarosh Umar","Prof. Nesar Ahmad","Prof. Ash Mohammad Abbas","Mr. Misbahur Rahman Warsi","Dr. Izharuddin","Dr. Rashid Ali","Dr. Saiful Islam","Mr. Nadeem Akhtar","Mr. Mohammed Abdul Qadeer","Mr. Tameem Ahmad","Mr. Asad Mohammad Khan","Mr. Muneeb Hasan Khan","Mr. Faisal Alam","Ms. Zeba Khanam","Ms. Hira Javed","Mr. Mohd. Imran","Mr. Misbahul Haque","Ms. Syeda Shira Moin","Mr. Mohd. Shoaib","Mr. Syed Usman Ahmad"};


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Computer");

        simpleList = (ListView) view.findViewById(R.id.simpleListView);
        final CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(), teacherNameList, teacherImages);
        simpleList.setAdapter(customAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("position"," "+position);

               // int value = (int)customAdapter.getItem(position);


                if(position==0){
                    Intent gotomap=new Intent();
                    gotomap.setClass(getContext(),LocationActivity.class);
                    Coordinate cr = new Coordinate("Sarosh Omar", "27.9157335", "78.0798319");
                    gotomap.putExtra("Editing",cr);
                    startActivity(gotomap);
                }
                else if(position==1){
                    Intent gotomap=new Intent();
                    gotomap.setClass(getContext(),LocationActivity.class);
                    Coordinate cr=new Coordinate("Nesar Ahmad","27.9158089","78.0797263");
                    gotomap.putExtra("Editing",cr);
                    startActivity(gotomap);
                }
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.activity_computer, container, false);



        return myview;

        /*simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent myIntent = new Intent(view.getContext(), Mapping.class);
                computer.this.getActivity.startActivityForResult(myIntent);
            }
        });*/
    }


}