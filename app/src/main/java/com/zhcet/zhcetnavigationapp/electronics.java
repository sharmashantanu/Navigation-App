package com.zhcet.zhcetnavigationapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class electronics extends Fragment {
    View myview;
    ListView simpleList;
    //Context context;
    //CustomAdapter listAdapter;

    public static int [] teacherImages={R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu};
    public static String [] teacherNameList={"Prof. Mohd. Hasan","Prof. Zia Ahmad Abbasi","Prof. Pervez Mustajab","Prof. Mirza Salim Beg","Prof. Omar Farooq","Prof. Muhmmad Shah Alam","Prof. Ekram Khan","Prof. Sudhanshu Maheshwari","Prof. Mohammad Jawaid Siddiqui","Prof. Syed Atiqur Rahman","Prof. Athar Ali Moinuddin","Prof. Syed Javed Arif","Dr.Tahira Parveen","Mr. Musiur Raza Abidi","Dr. Anwar Sadat","Dr. Mohd Samar Ansari","Dr. Mohd. Sharique","Dr. Naushad Alam","Mr. Mohd. Wajid Shamsi","Dr. M. Ayyub Khan","Ms Raaziyah Shamim","Dr. Rehan Muzammil"};


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Electronics");

        simpleList = (ListView) view.findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(), teacherNameList, teacherImages);
        simpleList.setAdapter(customAdapter);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.activity_electronics, container, false);


        return myview;
    }
}
