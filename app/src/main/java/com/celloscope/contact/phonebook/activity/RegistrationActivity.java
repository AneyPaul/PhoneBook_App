package com.celloscope.contact.phonebook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.celloscope.contact.phonebook.dao.DatabaseHandler;
import com.celloscope.contact.phonebook.R;
import com.celloscope.contact.phonebook.manager.RegistrationManager;

/**
 * Created by aney on 8/17/17.
 */

public class RegistrationActivity extends AppCompatActivity {

    EditText first, email, mobile, pass, confpass;
    Button save;
    DatabaseHandler db;

    AwesomeValidation awesomeValidation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_layout);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        first= (EditText)findViewById(R.id.edit_name);
        email=(EditText)findViewById(R.id.edit_email);
        mobile =(EditText)findViewById(R.id.edit_phone);
        pass=(EditText)findViewById(R.id.edit_pass);
        confpass=(EditText)findViewById(R.id.edit_confirmPass);

        save=(Button)findViewById(R.id.button_create);


        awesomeValidation.addValidation(this, R.id.edit_name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.edit_email, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.edit_pass, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.edit_phone, "^(?:\\+?88)?01[15-9]\\d{8}$", R.string.mobileerror);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                String edfirst = first.getText().toString();
                String edemail = email.getText().toString();
                String edmobile = mobile.getText().toString();
                String edpass = pass.getText().toString();
                String edConf = confpass.getText().toString();


                if(edConf.equals(edpass) && awesomeValidation.validate()){


                    RegistrationManager registrationManager= new RegistrationManager(RegistrationActivity.this);
                    registrationManager.saveRegistrationData(edfirst,edemail,edmobile,edpass);




                    Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();

                }
                else{

                    Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_LONG).show();
                    pass.setText("");
                    confpass.setText("");
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivityForResult(myIntent, 0);

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
