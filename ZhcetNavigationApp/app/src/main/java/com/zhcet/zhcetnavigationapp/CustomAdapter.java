package com.zhcet.zhcetnavigationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;




/**
 * Created by Shantanu on 12-11-2017.
 */



public class CustomAdapter extends BaseAdapter {
    Context context;
    String teacherNameList[];
    String getstatus[];
    int teacherImages[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] teacherNameList, int[] teacherImages, String[] getstatus) {
        this.context = context;
        this.teacherNameList = teacherNameList;
        this.teacherImages = teacherImages;
        this.getstatus = getstatus;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return teacherNameList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.listview_activity, null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textView1 = (TextView) view.findViewById(R.id.textView1);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        textView.setText(teacherNameList[i]);
        textView1.setText(getstatus[i]);
        icon.setImageResource(teacherImages[i]);
        return view;
    }
}
