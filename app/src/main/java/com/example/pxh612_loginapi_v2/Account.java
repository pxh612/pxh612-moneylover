package com.example.pxh612_loginapi_v2;

public class Account {
    public String username;
    String displayName;
    String password;
    int userID;


    public Account(){

    }
    public Account(String username, String password){
        this.username = username;
        this.password = password;

        return;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
