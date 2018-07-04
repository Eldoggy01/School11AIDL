package com.example.eldar.school11aidl;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

public class DataStorage {

    private final static String PREF_NAME = "FirstPref";
    private final static String USER_NAME = "UserName";
    private Context context;

    public DataStorage(Context context) {
        this.context = context;
    }

    public String getUserName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME, "Max");
    }

    public void setUserName(String userName){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME,userName);
        editor.commit();
    }
}
