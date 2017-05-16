package com.softgroup.softgrouptest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Created by Артем on 15.05.2017.
 */

public class MyDataStorage {
    private HashMap UserMap;
    private static MyDataStorage Instance;
    private MyDataStorage(){
        UserMap=new HashMap();
    }
    static MyDataStorage getInstance(){
        if (Instance==null)Instance=new MyDataStorage();
        return Instance;
    }
    public void AddToHash(String email, String password){
            UserMap.put(email, toMd5(password));
    }
    public boolean CheckEmail(String email){
       return UserMap.get(email)!=null;
    }
    public boolean CheckAutor(String email, String password){
        if(UserMap.get(email)==null) return false;
        return ((String)UserMap.get(email)).equals(toMd5(password));
    }
    private String toMd5(String str){
        StringBuffer sb = new StringBuffer();
        MessageDigest md = null;
        try {
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
