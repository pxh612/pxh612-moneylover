package com.example.pxh612_loginapi_v2;

import android.util.Base64;
import android.util.Log;

import com.example.pxh612_loginapi_v2.activity.LoginActivity;
import com.example.pxh612_loginapi_v2.database.ConsumerKey;
import com.example.pxh612_loginapi_v2.database.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AccessTokenAPI {


//    private static String username;
//    private static String password;

    public static LoginActivity.LOGIN_STATUS fetchIsSucessfully(String email, String password) {
//        this.username = username;
//        this.password = password;

        HttpURLConnection conn = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(URLs.TOKEN_API);
            conn = (HttpURLConnection) url.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            putRequestHeader(conn);
            putRequestBody(conn, email, password);

//
//            conn.setReadTimeout(10000);
//            conn.setConnectTimeout(15000);

            conn.setReadTimeout(1000);
            conn.setConnectTimeout(1500);

            conn.connect();
            Log.d("pass", "LoginAPI > fetch > conn.connect()");


            InputStream stream = conn.getInputStream();
            Log.d("pass", "LoginAPI > fetch >             InputStream stream = conn.getInputStream();");


            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";
            Log.d("pass", "LoginAPI > fetch > String line = \"\"");


            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
                Log.d("__ Response: ", "AccessTokenAPI > fetchIsSucessfully : " + line);   //here u ll get whole response...... :-)
            }

            JSONObject response = new JSONObject(buffer.toString());
            return fetchResponseIsSuccessful(response);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
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
        return LoginActivity.LOGIN_STATUS.NO_CONNECTION;
    }

    private static void putRequestBody(HttpURLConnection conn, String email, String password) {
        // a String in JSON format
        String jsonInputString = null;
//        jsonInputString = "{\n" +
//                "    \"email\": \"tien@1.vn\",\n" +
//                "    \"password\": \"123456\"\n" +
//                "}";

        //TODO: neater JSON convert

        jsonInputString = "{\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"password\": \"" + password + "\"\n" +
                "}";

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static LoginActivity.LOGIN_STATUS fetchResponseIsSuccessful(JSONObject response) {
        String status = null;
        try {
            status = response.getString("status");

            if(status.equals("true")){
                String access_token = response.getString("access_token");
                String expire = response.getString("expire");
                String refresh_token = response.getString("refresh_token");
                Log.d("__ String value:", "AccessTokenAPI > fetchResponseIsSuccessful > access_token : " + access_token);
                Log.d("__ String value:", "AccessTokenAPI > fetchResponseIsSuccessful > expire : " + expire);
                Log.d("__ String value:", "AccessTokenAPI > fetchResponseIsSuccessful > refresh_token : " + refresh_token);

                CurrentAccount.setAccessToken(access_token);
                CurrentAccount.setExpireDate(expire);
                CurrentAccount.setRefreshToken(refresh_token);

                return LoginActivity.LOGIN_STATUS.SUCCESSFUL;
            }
            else return LoginActivity.LOGIN_STATUS.INVALID;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return LoginActivity.LOGIN_STATUS.INVALID;
    }

    private static void putRequestHeader(HttpURLConnection conn) {
//        conn.setRequestProperty("Accept-Charset", "UTF-8");
//        conn.setRequestProperty("Authorization", getB64Auth(username, password));
        final String apiVersion = "4";
        final String client = ConsumerKey.username;
        final String Authorization = "Bearer " + CurrentAccount.getRequestToken();
        final String ContentType = "application/json";

        Log.d("__ String value: ", "AccessTokenAPI > putRequestHeader > client : " + client);
        Log.d("__ String value: ", "AccessTokenAPI > putRequestHeader > Authorization : " + Authorization);

        conn.setRequestProperty("apiVersion", apiVersion);
        conn.setRequestProperty("client", client);
        conn.setRequestProperty("Authorization", Authorization);
        conn.setRequestProperty("Content-Type", ContentType);

    }

    private static String getB64Auth (String login, String pass) {
        String source = login + ":" + pass;
        String ret = "Basic " + Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
        Log.i("__ String: ", "LoginAPI > getB64Auth > ret : " + ret);
        return ret;
    }
}
