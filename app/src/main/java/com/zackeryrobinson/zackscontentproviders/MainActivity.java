package com.zackeryrobinson.zackscontentproviders;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executeDatabase();
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

    public void executeDatabase() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String name = "Zack";
        String phone0 = "0";
        String phone1 = "1";
        String phone2 = "2";
        String phone3 = "3";
        String address = "address";
        String social = "social";
        String license = "license";
        String newName = "Lisa";
        databaseHelper.createContact(name, phone0, social, address, license);
        databaseHelper.createContact(name, phone1, social, address, license);
        databaseHelper.createContact(name, phone2, social, address, license);
        databaseHelper.readContact(2);
        databaseHelper.updateContact(newName, phone1);
        databaseHelper.readContact(1);
        databaseHelper.deleteContact(phone1);
        databaseHelper.readContact(1);
        //databaseHelper.deleteContact();

    }
}
