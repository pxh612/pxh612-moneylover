package com.example.pxh612_loginapi_v2.viewmodel;

import com.example.pxh612_loginapi_v2.CurrentAccount;
import com.example.pxh612_loginapi_v2.LoginAsyncTask;

public class LoginViewModel {

    public boolean gainAccessSuccessfully(String username, String password) {
//        new LoginAsyncTask().execute();
        CurrentAccount.setUsername(username);
        new LoginAsyncTask(username, password).execute();
        return false;
    }
}
