package com.celloscope.contact.phonebook.manager.ContactManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.celloscope.contact.phonebook.bean.Contact;
import com.celloscope.contact.phonebook.dao.DatabaseHandler;

import static android.content.ContentValues.TAG;

/**
 * Created by aney on 9/17/17.
 */

public class AddContact {

    public SQLiteDatabase database;
    public DatabaseHandler dbHelper;





    public AddContact(Context context) {
        dbHelper = new DatabaseHandler(context,null,null,2);

    }

    public void close() {
        dbHelper.close();
    }

    public void addContact(Contact addContactdata)

    {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_CONTACT_NAME,addContactdata.getName());
        values.put(DatabaseHandler.KEY_PHONE_NO, addContactdata.getMobNo());
        values.put(DatabaseHandler.KEY_IMAGE_PATH,addContactdata.getPath());


        long insert = database.insert(DatabaseHandler.TableAddContact, null, values);

        Log.d(TAG,"result "+ insert + " addData: Adding name:" + addContactdata.getName() +" Phn : " + addContactdata.getMobNo() + " and path " + addContactdata.getPath() +  " to " + DatabaseHandler.TableAddContact);


        database.close();

    }

    public void saveContactData(String contactName,String contactNo,String picturePath)
    {
        Contact contactData = new Contact();

        contactData.setName(contactName);
        contactData.setMobNo(contactNo);
        contactData.setPath(picturePath);

        addContact(contactData);
    }

}
