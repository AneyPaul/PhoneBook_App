package com.celloscope.contact.phonebook.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.celloscope.contact.phonebook.bean.Contact;
import com.celloscope.contact.phonebook.bean.Registration;
import com.celloscope.contact.phonebook.dao.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by aney on 9/17/17.
 */

public class LoginManager {

    public SQLiteDatabase database;
    public DatabaseHandler dbHelper;

    String password;



    public LoginManager(Context context) {
        dbHelper = new DatabaseHandler(context,null,null,2);

    }

    public void close() {
        dbHelper.close();
    }



    public String getregister(String username){
        database = dbHelper.getReadableDatabase();

        Cursor cursor=database.query(DatabaseHandler.TableRegistration,null, "email_id=?",new String[]{username},null, null, null, null);

        if(cursor.getCount()<1){
            cursor.close();
            return "Not Exist";
        }
        else if(cursor.getCount()>=1 && cursor.moveToFirst()){

            password = cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_PASSWORD));
            cursor.close();

        }
        return password;


    }




}
