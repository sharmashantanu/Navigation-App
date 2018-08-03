package com.zhcet.zhcetnavigationapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CustomAdapter extends BaseAdapter {
    Context context;
    String teacherNameList[];
    int teacherImages[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] teacherNameList, int[] teacherImages) {
        this.context = context;
        this.teacherNameList = teacherNameList;
        this.teacherImages = teacherImages;
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
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        textView.setText(teacherNameList[i]);
        icon.setImageResource(teacherImages[i]);
        return view;
    }
}
