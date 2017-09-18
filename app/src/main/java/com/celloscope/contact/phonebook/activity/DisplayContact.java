package com.celloscope.contact.phonebook.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.celloscope.contact.phonebook.bean.Contact;
import com.celloscope.contact.phonebook.dao.DatabaseHandler;
import com.celloscope.contact.phonebook.utils.ImageAdapter;
import com.celloscope.contact.phonebook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aney on 9/7/17.
 */

public class DisplayContact extends AppCompatActivity {

    private ArrayList<Contact> images = new ArrayList();
    private ImageAdapter imageAdapter;
    private ListView listView;

    List<Contact> contacts;

    DatabaseHandler db;
    String phones;
    String names;

    String phone;

    com.celloscope.contact.phonebook.manager.ContactManager.DisplayContact displayContactManager= new com.celloscope.contact.phonebook.manager.ContactManager.DisplayContact(DisplayContact.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);


        contacts = displayContactManager.getAllContacts();


        for (Contact cn : contacts) {

            phones = cn.getMobNo();
            names = cn.getName();
            images.add(cn);

        }



        imageAdapter = new ImageAdapter(this, R.layout.list_item, images);

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(imageAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {



                phone = "tel:" + phones;
                final String name = names;

                final Dialog dialog = new Dialog(DisplayContact.this);
                dialog.setContentView(R.layout.popup);
                dialog.setTitle("Contact");
                dialog.show();
                Button btnExit = (Button) dialog.findViewById(R.id.btnExit);
                btnExit.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.findViewById(R.id.btnCall)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override public void onClick(View v) {
                                //CALL Start

                                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(phone));

                                if (ActivityCompat.checkSelfPermission(DisplayContact.this,
                                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                startActivity(i);
                                //CALL END
                            }
                        });
                dialog.findViewById(R.id.btnSMS)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override public void onClick(View v) {
                                //SMS Start
                                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
                                startActivity(smsIntent);

                                //SMS END
                            }
                        });

                dialog.findViewById(R.id.btnEmail)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override public void onClick(View v) {
                                sendEmail();
                            }
                        });

                dialog.findViewById(R.id.btnDelete)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override public void onClick(View v) {

                                displayContactManager.deleteContact(images.get(position));

                                Intent i = new Intent(DisplayContact.this,DisplayContact.class);
                                startActivity(i);
                                finish();


                            }
                        });

            }

        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DisplayContact.this, AddContactActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(DisplayContact.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                imageAdapter.filter(searchQuery.toString().trim());
                listView.invalidate();
                return true;
            }
        });




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_search) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}


