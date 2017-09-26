package com.zackeryrobinson.contentclient;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ClientMainActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Hi!");
        testContentProvider();

    }

    private void testContentProvider() {
        Uri uri = Uri.parse("content://com.zackeryrobinson.PROVIDER");

        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));

            Log.d(TAG, "testContentProvider: " + name);
        }

    }
}
