package com.example.pxh612_loginapi_v2;

import android.os.AsyncTask;

import com.example.pxh612_loginapi_v2.activity.LoginActivity;
import com.example.pxh612_loginapi_v2.database.ConsumerKey;

public class LoginAsyncTask extends AsyncTask<Void, Void, LoginActivity.LOGIN_STATUS> {

    LoginAsyncTaskListener listener;

    private String email;
    private String password;

    public LoginAsyncTask(LoginAsyncTaskListener listener, String email, String password){
        this.email = email;
        this.password = password;
        this.listener = listener;
    }

    @Override
    protected LoginActivity.LOGIN_STATUS doInBackground(Void... voids) {
        RequestTokenAPI.fetchIsSucessfully(ConsumerKey.username, ConsumerKey.password);
        return AccessTokenAPI.fetchIsSucessfully(email, password);
//        LoginAPIFake.fetch();
    }

    protected void onPostExecute(LoginActivity.LOGIN_STATUS result){
        listener.onLoginAsyncTaskPostExecute(result);
        return;
    }

}
