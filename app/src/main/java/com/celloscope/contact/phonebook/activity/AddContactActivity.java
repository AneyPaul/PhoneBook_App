package com.celloscope.contact.phonebook.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.celloscope.contact.phonebook.bean.Contact;
import com.celloscope.contact.phonebook.manager.ContactManager.AddContact;
import com.celloscope.contact.phonebook.dao.DatabaseHandler;
import com.celloscope.contact.phonebook.R;

public class AddContactActivity extends ActionBarActivity {


    private static final int RESULT_LOAD_IMAGE = 1;

    ImageButton imageButton;
    Button displayContact;
    EditText name, phoneNo, email, address, dept;

    String picturePath;
    DatabaseHandler db;
    Contact reg = new Contact();

    Bitmap mBitmap;
    byte imageInByte[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);


        name = (EditText) findViewById(R.id.editname);
        phoneNo = (EditText) findViewById(R.id.editphoneno);
        email = (EditText) findViewById(R.id.editemailID);
        address = (EditText) findViewById(R.id.editAddress);
        dept = (EditText) findViewById(R.id.editdeptName);

        imageButton = (ImageButton) findViewById(R.id.imageButton1);
        displayContact = (Button) findViewById(R.id.displaybutton);



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callGallery();
            }

        });



        displayContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String contactName = name.getText().toString();
                String contactNo = phoneNo.getText().toString();
                String Email = email.getText().toString();
                String Address = address.getText().toString();
                String Dept = dept.getText().toString();

                if(contactName != null || contactNo != null )
                {

                    AddContact addContactManager= new AddContact(AddContactActivity.this);
                    addContactManager.saveContactData(contactName,contactNo,picturePath);


                Toast.makeText(getApplicationContext(), "Contact added", Toast.LENGTH_LONG).show();
                Intent i = new Intent(AddContactActivity.this,DisplayContact.class);
                startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Contact is empty", Toast.LENGTH_LONG).show();
                }

            }
        });
    }




    private void callGallery()
  {
      Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      startActivityForResult(i,RESULT_LOAD_IMAGE);
  }
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
       super.onActivityResult(requestCode,resultCode,data);
       if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data)
       {
            Uri selectedImage = data.getData();
           String[] filePathColumn = {MediaStore.Images.Media.DATA};
           Cursor cursor = getContentResolver().query(selectedImage,filePathColumn,null,null,null);
           cursor.moveToFirst();
           int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
           cursor.close();

           int width = imageButton.getWidth();
           int height = imageButton.getHeight();

           Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
           imageButton.setImageBitmap(Bitmap.createScaledBitmap(bitmap,width,height,false));


       }
  }


}