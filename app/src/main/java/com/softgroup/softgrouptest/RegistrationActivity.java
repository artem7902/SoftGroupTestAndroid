package com.softgroup.softgrouptest;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.softgroup.softgrouptest.com.softgroup.softgrouptest.database.DatabaseHandler;
import com.softgroup.softgrouptest.com.softgroup.softgrouptest.database.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationActivity extends AppCompatActivity {
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db = new DatabaseHandler(this);
    }
    public void registerClick(View view){
        EditText email=(EditText) findViewById(R.id.email_input);
        EditText password=(EditText) findViewById(R.id.password_input);
        EditText password_confirm=(EditText) findViewById(R.id.password_conf_input);
        if(email.getText().toString().length()<6)
            new AlertDialog.Builder(this).setTitle("Ошибка")
                .setMessage("Email содержит меньше 6 символов!")
                .setPositiveButton("Ok", null)
                .show();
        else if(password.getText().toString().length()<3)
            new AlertDialog.Builder(this).setTitle("Ошибка")
                    .setMessage("Пароль содержит меньше 3 символов!")
                    .setPositiveButton("Ok", null)
                    .show();
        else if(!password.getText().toString().equals(password_confirm.getText().toString()))
            new AlertDialog.Builder(this).setTitle("Ошибка")
                    .setMessage("Пароль и подтверждение не совпадают!")
                    .setPositiveButton("Ok", null)
                    .show();
        else if(db.getUser(email.getText().toString())!=null)
            new AlertDialog.Builder(this).setTitle("Ошибка")
                    .setMessage("Такой email уже зарегистрирован!")
                    .setPositiveButton("Ok", null)
                    .show();
        else{
            db.addUser(new User(email.getText().toString(), password.getText().toString()));
            this.finish();
        }
    }
}
