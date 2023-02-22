package com.example.pxh612_loginapi_v2;

import android.os.AsyncTask;

public class LoginAsyncTask extends AsyncTask<Void, Void, String> {

    private String username;
    private String password;

    public LoginAsyncTask(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    protected String doInBackground(Void... voids) {
        LoginAPI.fetch(username, password);
//        LoginAPIFake.fetch();
        return null;
    }

    protected void onPostExecute(String response){
        //TODO
        return;
    }




}
