package com.example.pxh612_loginapi_v2;


import android.util.Log;

public class CurrentAccount {

    private static String requestToken;
    private static String accessToken = null;
    private static String expireDate;
    private static String refreshToken;

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
}
