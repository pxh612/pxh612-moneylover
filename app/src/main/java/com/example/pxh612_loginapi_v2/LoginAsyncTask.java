package com.example.pxh612_loginapi_v2;

import android.os.AsyncTask;

import com.example.pxh612_loginapi_v2.database.ConsumerKey;

public class LoginAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private String email;
    private String password;

    public LoginAsyncTask(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        RequestTokenAPI.fetchIsSucessfully(ConsumerKey.username, ConsumerKey.password);
        return AccessTokenAPI.fetchIsSucessfully(email, password);
//        LoginAPIFake.fetch();
    }

    protected void onPostExecute(Boolean result){
        //TODO: return True on Success Authorization
        return;
    }




}
