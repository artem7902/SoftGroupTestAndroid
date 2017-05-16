package com.softgroup.softgrouptest;

import android.view.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ((TextView) findViewById(R.id.myemail)).append((String)getIntent().getSerializableExtra("email"));
    }

    public void LogOutClick(View view){
        this.finish();
    }
}
