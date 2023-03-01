package com.example.pxh612_loginapi_v2.repository;


import android.content.Context;
import android.util.Log;

import com.example.pxh612_loginapi_v2.Account;
import com.example.pxh612_loginapi_v2.databasehelper.KeyValueDatabaseHelper;

public class CurrentAccount {

    private static String requestToken;
    private static String accessToken = null;
    private static String expireDate;
    private static String refreshToken;
    private static KeyValueDatabaseHelper keyValueDatabaseHelper;
    //    private static
    static Account account = new Account();
    private static boolean isLoggedIn = false;

    public CurrentAccount(Context context) {
        keyValueDatabaseHelper = KeyValueDatabaseHelper.getInstance(context);
    }

    public static boolean isNotLoggedIn() {
        return !isLoggedIn;
    }

    public static String getDisplayName() {
//        return account.displayName;
        return account.username;
    }

    public static String getUsername() {
        return account.getUsername();
    }

    public static void reset() {
        account = new Account();
        isLoggedIn = false;
        keyValueDatabaseHelper.deleteKeyValue("username");
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void setRequestToken(String request_token) {
        requestToken = request_token;
        Log.i("__ String value: ", "CurrentAccount > requestToken : " + requestToken);
    }

    public static String getRequestToken() {
        return requestToken;
    }

    public static void setAccessToken(String access_token) {
        accessToken = access_token;
    }

    public static void setExpireDate(String expire) {
        expireDate = expire;
    }

    public static void setRefreshToken(String refresh_token) {
        refreshToken = refresh_token;
    }

    public static void setUsername(String username) {
        account.username = username;
    }


    public static boolean isCheckedLoggedIn() {
        String username = null;
        username = keyValueDatabaseHelper.getKeyValue("username");
        if (username == null) {
            isLoggedIn = false;
            return false;
        } else {
            setUsername(username);
            isLoggedIn = true;
            return true;
        }
    }

    public static boolean isCheckedNotLoggedIn() {
        return !isCheckedLoggedIn();
    }

    public static void updateDatabase() {
        keyValueDatabaseHelper.insertKeyValue("username", getUsername());
//        keyValueDatabaseHelper.addKeyValue(getUsername());
    }
}