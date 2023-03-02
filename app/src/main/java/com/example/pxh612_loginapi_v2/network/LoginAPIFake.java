package com.example.pxh612_loginapi_v2.network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginAPIFake {
    private static final String dogAPIMistake="https://dog.ceo/dog-api/";
    private static final String dogAPI="https://dog.ceo/api/breeds/image/random";
    private static final String TOKEN_API = "https://oauth-sandbox.moneylover.me/token";

    public static String fetch(){


        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(dogAPI);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            Log.d("pass", "LoginAPIFake > fetch > connection.connect()");


            InputStream stream = connection.getInputStream();
            Log.d("pass", "LoginAPIFake > fetch >             InputStream stream = connection.getInputStream();");


            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";
            Log.d("pass", "LoginAPIFake > fetch > String line = \"\"");


            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", " " + line);   //here u ll get whole response...... :-)
            }

            JSONObject response = new JSONObject(buffer.toString());
//            String message = response.getString("message");
//            String status = response.getString("status");
//
//            if(status.equals("success")){
//                return message;
//            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
