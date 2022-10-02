package com.example.contentproviderproject929;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolver=getContentResolver();
    }

    public void addUser(View view) {
        Uri uri1Update= Uri.parse("content://com.example.contentproviderproject929.Test/Update");
        ContentValues contentValues=new ContentValues();
        contentValues.put("name","你好");
        resolver.update(uri1Update,contentValues,"id=?",new String[]{14+""});
    }
}