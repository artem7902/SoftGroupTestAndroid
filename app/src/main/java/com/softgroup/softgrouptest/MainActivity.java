package com.softgroup.softgrouptest;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sing_inClick(View view ){
        final EditText email = (EditText) findViewById(R.id.email_input);
        final EditText password = (EditText) findViewById(R.id.password_input);
            if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || !MyDataStorage.getInstance().CheckAutor(email.getText().toString(), password.getText().toString()))
            new AlertDialog.Builder(this).setTitle("Ошибка")
                    .setMessage("Неверный логин и/или пароль!")
                    .setPositiveButton("Ok", null)
                    .show();
            else {
                    Intent loginInt = new Intent(this, AccountActivity.class);
                    loginInt.putExtra("email", email.getText().toString());
                    startActivity(loginInt);
            }
        }
    public void registerClick(View view){
        Intent Reg=new Intent(this, RegistrationActivity.class);
        startActivity(Reg);
    }
}
