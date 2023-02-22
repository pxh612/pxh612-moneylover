package com.example.pxh612_loginapi_v2;


import android.util.Log;

public class CurrentAccount {

    private static String requestToken;

    static Account account = new Account();
    private static boolean isLoggedIn = false;

    public static boolean isNotLoggedIn() {
        return !isLoggedIn;
    }

    public static void gainAccessSuccessfully(int userID){
        //TODO
    }

    public static String getDisplayName() {
        return account.displayName;
    }

    public static String getUsername() {
        return account.getUsername();
    }

    public static void reset() {
        account = new Account();
        isLoggedIn = false;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void setRequestToken(String request_token) {
        requestToken = request_token;
        Log.d("String value: ", "CurrentAccount > requestToken : " + requestToken);
    }
}
