package com.example.pxh612_loginapi_v2.viewmodel;

import com.example.pxh612_loginapi_v2.repository.CurrentAccount;
import com.example.pxh612_loginapi_v2.network.LoginAsyncTask;
import com.example.pxh612_loginapi_v2.network.LoginAsyncTaskListener;

public class LoginViewModel {

//    public boolean gainAccessSuccessfully(String username, String password) {
////        new LoginAsyncTask().execute();
//        CurrentAccount.setUsername(username);
//        new LoginAsyncTask(username, password).execute();
//        return false;
//    }

    public void connectToServer(LoginAsyncTaskListener listener, String username, String password) {
        CurrentAccount.setUsername(username);
        new LoginAsyncTask(listener, username, password).execute();
        return ;
    }

    public void addToAccountResository() {
        CurrentAccount.updateDatabase();
    }
}
