package com.example.pxh612_loginapi_v2;

import android.os.AsyncTask;

public class LoginAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private String username;
    private String password;

    public LoginAsyncTask(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return RequestTokenAPI.fetchIsSucessfully(username, password);
//        LoginAPIFake.fetch();
    }

    protected void onPostExecute(Boolean result){
        //TODO
        return;
    }




}
