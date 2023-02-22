package com.example.pxh612_loginapi_v2;


public class CurrentAccount {

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
}
