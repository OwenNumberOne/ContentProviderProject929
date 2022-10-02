package com.example.contentproviderproject929.application;

import android.app.Application;
import android.content.Context;

public class MyApplication  extends Application {

    public static Context getmContext() {
        return mContext;
    }

    public  static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
    }
}
