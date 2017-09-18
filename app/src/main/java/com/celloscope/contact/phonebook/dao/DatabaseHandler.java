package com.celloscope.contact.phonebook.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aney on 9/6/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";

    SQLiteDatabase db;
    String password;

    public DatabaseHandler(Context context, Object name,
                           Object factory, int version) {
        // TODO Auto-generated constructor stub
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }




    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Mydatabase.db";


    public static final String TableAddContact = "addContact";

    public static final String KEY_ID = "id";
    public static final String KEY_CONTACT_NAME = "contactName";
    public static final String KEY_PHONE_NO = "phone_number";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_DEPT = "dept";
    public static final String KEY_IMAGE_PATH = "path";


    //table Registration
    public static final String TableRegistration = "Registration";
    public static final String KEY_NAME = "name";
    public static final String KEY_MOB_NO = "mobile_number";
    public static final String KEY_EMAIL_ID = "email_id";
    public static final String KEY_PASSWORD = "password";


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

       String createTable = "CREATE TABLE " + TableAddContact + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CONTACT_NAME + " TEXT, " + KEY_PHONE_NO + " TEXT, " + KEY_IMAGE_PATH + " TEXT " + ")";

        String createTableRegistration = "CREATE TABLE " + TableRegistration + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, " + KEY_EMAIL_ID + " TEXT, " + KEY_MOB_NO + " TEXT, " + KEY_PASSWORD + " TEXT " + ")";

        db.execSQL(createTable);
        db.execSQL(createTableRegistration);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS " + TableAddContact);

        db.execSQL("DROP TABLE IF EXISTS " + TableRegistration);

        onCreate(db);
    }



    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static String getKeyId() { return KEY_ID; }

    public static String getTableContacts() {
        return TableAddContact;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }


}
