package com.zackeryrobinson.zackscontentproviders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Zack on 9/25/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "myDatabase";
    public static final String TABLE_NAME = "Contacts";
    public static final String CONTACT_NAME = "name";
    public static final String CONTACT_PHONE = "phone";
    public static final String CONTACT_SOCIAL = "social";
    public static final String CONTACT_ADDRESS = "address";
    public static final String CONTACT_LICENSE = "license";
    private static final String TAG = "DatabaseHelperTag";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + CONTACT_NAME + " TEXT," + CONTACT_PHONE + " TEXT PRIMARY KEY," + CONTACT_SOCIAL + " TEXT," + CONTACT_ADDRESS + " TEXT," + CONTACT_LICENSE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void createContact(String name, String phone, String social, String address, String license){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_NAME, name);
        contentValues.put(CONTACT_PHONE, phone);
        contentValues.put(CONTACT_SOCIAL, social);
        contentValues.put(CONTACT_ADDRESS, address);
        contentValues.put(CONTACT_LICENSE, license);
        long isSaved = db.insert(TABLE_NAME,null,contentValues);
        Log.d(TAG, "saveNewContact: " + isSaved);
    }

    public void readContact(int position){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();
        cursor.move(position);
        Log.d(TAG, "getContacts: " + "name: " + cursor.getString(0) + "phone: " + cursor.getString(1)+ "social: " + cursor.getString(2) + "address: " + cursor.getString(3)+ "license: " + cursor.getString(4));



    }

    public void updateContact(String newName, String key){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTACT_NAME, newName);
        String whereClause = CONTACT_PHONE + " LIKE ?";
        String[] whereArgs = { key };

        sqLiteDatabase.update(
                TABLE_NAME,
                values,
                whereClause,
                whereArgs
        );
    }

    public void deleteContact(String key){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String whereClause = CONTACT_PHONE + " LIKE ?";
        String[] whereArgs = { key };

        sqLiteDatabase.delete(
                TABLE_NAME,
                whereClause,
                whereArgs
        );
    }

}