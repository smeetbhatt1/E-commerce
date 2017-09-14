package com.example.smeet.productfinal;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_USER_ID="user_id";
    private static final String KEY_USER_NAME="user_name";
    private static final String KEY_USER_SURNAME="user_surname";
    private static final String KEY_USER_EMAIL="user_email";
    private static final String KEY_USER_PHONE="user_phone";
    private static final String KEY_USER_ADDRESS="user_address";
    private static final String KEY_USER_CITY="user_city";
    private static final String KEY_USER_STATE="user_state";
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;

    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
    public boolean userLogin(int user_id,String user_name,String user_surname,String user_email,String user_phone,
                             String user_address,String user_city,String user_state){

        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(KEY_USER_ID,user_id);
        editor.putString(KEY_USER_NAME,user_name);
        editor.putString(KEY_USER_SURNAME,user_surname);
        editor.putString(KEY_USER_EMAIL,user_email);
        editor.putString(KEY_USER_PHONE,user_phone);
        editor.putString(KEY_USER_ADDRESS,user_address);
        editor.putString(KEY_USER_CITY,user_city);
        editor.putString(KEY_USER_STATE,user_state);
        editor.apply();
        return true;
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_EMAIL,null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public String getUser_Name(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_NAME,null);
    }
    public String getUser_Surname(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_SURNAME,null);
    }
    public String getUser_Email() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public String getUser_Phone() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_PHONE, null);
    }

    public String getUser_Address() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_ADDRESS, null);
    }

    public String getUser_City() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_CITY, null);
    }

    public String getUser_State() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_STATE, null);
    }
}
