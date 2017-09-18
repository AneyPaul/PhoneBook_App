package com.celloscope.contact.phonebook.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.celloscope.contact.phonebook.bean.Registration;
import com.celloscope.contact.phonebook.dao.DatabaseHandler;

/**
 * Created by aney on 9/17/17.
 */

public class RegistrationManager {


    public SQLiteDatabase database;
    public DatabaseHandler dbHelper;





    public RegistrationManager(Context context) {
        dbHelper = new DatabaseHandler(context,null,null,2);

    }

    public void close() {
        dbHelper.close();
    }


    public long addRegistration(Registration addContactdata)
    {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_NAME,addContactdata.getfirstName());
        values.put(DatabaseHandler.KEY_EMAIL_ID, addContactdata.getEmailId());
        values.put(DatabaseHandler.KEY_MOB_NO, addContactdata.getMobNo());
        values.put(DatabaseHandler.KEY_PASSWORD, addContactdata.getPassword());

        return database.insert(DatabaseHandler.TableRegistration,null,values);

    }

    public void saveRegistrationData(String edfirst,String edemail,String edmobile,String edpass)
    {
        Registration reg = new Registration();

        reg.setfirstName(edfirst);
        reg.setEmailId(edemail);
        reg.setMobNo(edmobile);
        reg.setPassword(edpass);

        addRegistration(reg);
    }

}
