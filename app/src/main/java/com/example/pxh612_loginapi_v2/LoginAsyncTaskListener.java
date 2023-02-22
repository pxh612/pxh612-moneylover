package com.example.pxh612_loginapi_v2;

import com.example.pxh612_loginapi_v2.activity.LoginActivity;

public interface LoginAsyncTaskListener {
    void onLoginAsyncTaskPostExecute(LoginActivity.LOGIN_STATUS result);
}
