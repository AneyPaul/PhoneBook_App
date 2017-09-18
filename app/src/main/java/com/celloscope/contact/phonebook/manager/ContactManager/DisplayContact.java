package com.celloscope.contact.phonebook.manager.ContactManager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.celloscope.contact.phonebook.bean.Contact;
import com.celloscope.contact.phonebook.dao.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aney on 9/17/17.
 */

public class DisplayContact {

    public SQLiteDatabase database;
    public DatabaseHandler dbHelper;





    public DisplayContact(Context context) {
        dbHelper = new DatabaseHandler(context,null,null,2);

    }

    public void close() {
        dbHelper.close();
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();

        String selectQuery = "SELECT * FROM addContact ORDER BY contactName";


        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_CONTACT_NAME)));
                contact.setMobNo(cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_PHONE_NO)));
                contact.setPath(cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_IMAGE_PATH)));

                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        database.close();

        return contactList;
    }


    public void deleteContact(Contact contact) {
        database = dbHelper.getWritableDatabase();
        database.delete(DatabaseHandler.TableAddContact, DatabaseHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        database.close();
    }
}
