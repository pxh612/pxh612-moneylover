package com.example.pxh612_loginapi_v2;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.PrecomputedText;
import android.util.Log;

import androidx.core.text.PrecomputedTextCompat;

public class LoginAsyncTask extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
//        LoginAPI.fetch();
        LoginAPIFake.fetch();
        return null;
    }

    protected void onPostExecute(String response){
        //TODO
        return;
    }




}
