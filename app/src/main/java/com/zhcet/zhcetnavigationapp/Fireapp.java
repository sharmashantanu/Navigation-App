package com.zhcet.zhcetnavigationapp;

import android.app.Application;

//import com.google.firebase.client.Firebase;

/**
 * Created by prashant on 10/11/17.
 */

public class Fireapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

       // Firebase.setAndroidContext(this);
    }
}
