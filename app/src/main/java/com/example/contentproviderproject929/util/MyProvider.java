package com.example.contentproviderproject929.util;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.contentproviderproject929.db.MyDBHelper;

public class MyProvider extends ContentProvider {

    SQLiteDatabase db;
    MyDBHelper helper;

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        Log.d("TT", "static initializer:-------------------------- ");
        matcher.addURI("com.example.contentproviderproject929.Test", "Add", 1);
        matcher.addURI("com.example.contentproviderproject929.Test", "Query", 2);
        matcher.addURI("com.example.contentproviderproject929.Test", "Query/*", 3);
        matcher.addURI("com.example.contentproviderproject929.Test", "Delete", 4);
        matcher.addURI("com.example.contentproviderproject929.Test", "Update", 5);


    }

    @Override
    public boolean onCreate() {

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        helper = MyDBHelper.getDB();
        db = helper.getReadableDatabase();
        Cursor cursor = null;
        switch (matcher.match(uri)) {
            case 2:
                cursor = db.query("UserInfo", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 3:
                cursor = db.query("UserInfo", projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d("TAG", "insert: -*-------------");
        switch (matcher.match(uri)) {
            case 1:
                helper = MyDBHelper.getDB();
                Log.d("TAG", "insert: ----" + helper);
                db = helper.getWritableDatabase();
                long rowId = db.insert("UserInfo", null, values);
                if (rowId > 0) {
                    Uri nameUri = ContentUris.withAppendedId(uri, rowId);
                    getContext().getContentResolver().notifyChange(nameUri, null);
                    return nameUri;
                }
                break;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        helper = MyDBHelper.getDB();
        db = helper.getReadableDatabase();

        int rowID=0;
        switch (matcher.match(uri)) {
            case 4:
                rowID= db.delete("UserInfo", selection, selectionArgs);
                break;
        }
        return  rowID;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        helper = MyDBHelper.getDB();
        db = helper.getReadableDatabase();

        int rId=0;
        switch (matcher.match(uri)){
            case 5:

                rId=db.update("UserInfo",values,selection,selectionArgs);
                break;
        }
        return rId;
    }
}
