package com.softgroup.softgrouptest.com.softgroup.softgrouptest.database;

/**
 * Created by Артем on 16.05.2017.
 */

public class User {
    private String _mail;
    private String _password;
    public User(){

    }
    public User(String email, String password){
        this.set_mail(email);
        this.set_password(password);
    }

    public String get_mail() {
        return _mail;
    }

    public void set_mail(String _mail) {
        this._mail = _mail;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
