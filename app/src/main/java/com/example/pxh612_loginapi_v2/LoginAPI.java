package com.example.pxh612_loginapi_v2;

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

public class LoginAPI {
    private static final String dogAPIMistake="https://dog.ceo/dog-api/";
    private static final String dogAPI="https://dog.ceo/api/breeds/image/random";
    private static final String TOKEN_API = "https://oauth-sandbox.moneylover.me/token";

    public static String fetch(){


        HttpURLConnection conn = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(TOKEN_API);
            conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", "UTF-8");

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.connect();
            Log.d("pass", "LoginAPI > fetch > conn.connect()");


            InputStream stream = conn.getInputStream();
            Log.d("pass", "LoginAPI > fetch >             InputStream stream = conn.getInputStream();");


            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";
            Log.d("pass", "LoginAPI > fetch > String line = \"\"");


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
            if (conn != null) {
                conn.disconnect();
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
