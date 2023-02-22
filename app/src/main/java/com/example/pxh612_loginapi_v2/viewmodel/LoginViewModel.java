package com.example.pxh612_loginapi_v2.viewmodel;

import com.example.pxh612_loginapi_v2.LoginAsyncTask;

public class LoginViewModel {

    public boolean gainAccessSuccessfully(String username, String password) {
        new LoginAsyncTask().execute();
        return false;
    }
}
