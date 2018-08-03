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

public class electronics extends Fragment {
    View myview;
    ListView simpleList;
    //Context context;
    //CustomAdapter listAdapter;

    public static int [] teacherImages={R.drawable.hasan,R.drawable.abbasi,R.drawable.pervez,R.drawable.mirza,R.drawable.omar,R.drawable.shah,R.drawable.ekram,R.drawable.mahesh,R.drawable.jawed,R.drawable.syed,R.drawable.athar,R.drawable.javed,R.drawable.parveen,R.drawable.raza,R.drawable.anwar,R.drawable.samar,R.drawable.mohd,R.drawable.naushad,R.drawable.wajid,R.drawable.ayyub,R.drawable.amu,R.drawable.rehan};
    public static String [] teacherNameList={"Prof. Mohd. Hasan","Prof. Zia Ahmad Abbasi","Prof. Pervez Mustajab","Prof. Mirza Salim Beg","Prof. Omar Farooq","Prof. Muhmmad Shah Alam","Prof. Ekram Khan","Prof. Sudhanshu Maheshwari","Prof. Mohammad Jawaid Siddiqui","Prof. Syed Atiqur Rahman","Prof. Athar Ali Moinuddin","Prof. Syed Javed Arif","Dr.Tahira Parveen","Mr. Musiur Raza Abidi","Dr. Anwar Sadat","Dr. Mohd Samar Ansari","Dr. Mohd. Sharique","Dr. Naushad Alam","Mr. Mohd. Wajid Shamsi","Dr. M. Ayyub Khan","Ms Raaziyah Shamim","Dr. Rehan Muzammil"};
    public static String[] getstatus={"Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active","Not Active"};


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Electronics");

        simpleList = (ListView) view.findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(), teacherNameList, teacherImages, getstatus);
        simpleList.setAdapter(customAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.v("position"," "+position);

                if(position==0){
                    Intent gotomap=new Intent();
                    gotomap.setClass(getContext(),LocationActivity.class);
                    Coordinate cr = new Coordinate("Sarosh Omar", "27.9157335", "78.0798319","false");
                    gotomap.putExtra("Editing",cr);
                    startActivity(gotomap);
                }
                else if(position==1){
                    Intent gotomap=new Intent();
                    gotomap.setClass(getContext(),LocationActivity.class);
                    Coordinate cr=new Coordinate("Nesar Ahmad","27.9158089","78.0797263","false");
                    gotomap.putExtra("Editing",cr);
                    startActivity(gotomap);
                }



            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.activity_electronics, container, false);
        return myview;
    }
}
