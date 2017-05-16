package com.softgroup.softgrouptest.com.softgroup.softgrouptest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Артем on 16.05.2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "accounts";
    private static final String TABLE_USERS = "users";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "( id integer primary key autoincrement," + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, contact.get_mail());
        values.put(KEY_PASSWORD, toMd5(contact.get_password()));
        db.insert(TABLE_USERS, null, values);
        db.close();
    }
    public User getUser(String Email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_EMAIL, KEY_PASSWORD }, KEY_EMAIL + "=?",
                new String[] { Email }, null, null, null, null);
        User user=null;
        if (cursor != null){
            cursor.moveToFirst();
            if(cursor.getCount()!=0)
            user = new User(cursor.getString(0), cursor.getString(1));
            cursor.close();
        }
        db.close();
        return user;
    }
    public boolean ValidateUser(String Email, String password) {
        return getUser(Email) != null && getUser(Email).get_password().equals(toMd5(password));
    }
    private String toMd5(String str){
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = null;
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
