package com.example.contentproviderproject929.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.contentproviderproject929.application.MyApplication;

public class MyDBHelper extends SQLiteOpenHelper {

    public static MyDBHelper myDBHelper;

    public MyDBHelper(@Nullable Context context) {
        super(context, "Test.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table UserInfo(id integer primary key autoincrement ,name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists UserInfo");
        onCreate(db);
    }

    public static MyDBHelper getDB() {
        if (myDBHelper == null) {
        }
        myDBHelper = new MyDBHelper(MyApplication.getmContext());

        return myDBHelper;
    }
}
