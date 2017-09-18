package com.celloscope.contact.phonebook.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.celloscope.contact.phonebook.manager.LoginManager;
import com.celloscope.contact.phonebook.dao.DatabaseHandler;
import com.celloscope.contact.phonebook.R;

public class LoginActivity extends AppCompatActivity {

    Button reg_button;

    EditText user, pass;
    Button login;
    DatabaseHandler db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user =(EditText)findViewById(R.id.edit_userID);
        pass = (EditText)findViewById(R.id.editPassword);
        login =(Button)findViewById(R.id.button_login);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                db=new DatabaseHandler(LoginActivity.this, null, null, 2);
                String username=user.getText().toString();
                String password= pass.getText().toString();



                LoginManager contactManager= new LoginManager(LoginActivity.this);
                String storedPassword = contactManager.getregister(username);



                if(password.equals(storedPassword)){

                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this,DisplayContact.class);
                    startActivity(i);
                    finish();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Username/Password incorrect", Toast.LENGTH_LONG).show();
                    user.setText("");
                    pass.setText("");
                }
            }
        });

        reg_button = (Button)findViewById(R.id.button_Reg);
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


}
