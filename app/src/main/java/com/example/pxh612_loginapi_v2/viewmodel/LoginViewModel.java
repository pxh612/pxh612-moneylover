package com.example.pxh612_loginapi_v2.viewmodel;

import com.example.pxh612_loginapi_v2.CurrentAccount;
import com.example.pxh612_loginapi_v2.LoginAsyncTask;
import com.example.pxh612_loginapi_v2.LoginAsyncTaskListener;

public class LoginViewModel {

//    public boolean gainAccessSuccessfully(String username, String password) {
////        new LoginAsyncTask().execute();
//        CurrentAccount.setUsername(username);
//        new LoginAsyncTask(username, password).execute();
//        return false;
//    }

    public void gainAccessToServer(LoginAsyncTaskListener listener, String username, String password) {
        CurrentAccount.setUsername(username);
        new LoginAsyncTask(listener, username, password).execute();
        return ;
    }
}
