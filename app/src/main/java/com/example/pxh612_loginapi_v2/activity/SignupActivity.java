package com.example.pxh612_loginapi_v2.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pxh612_loginapi_v2.R;
import com.example.pxh612_loginapi_v2.database.Messages;
import com.example.pxh612_loginapi_v2.fragment.MyDialogFragment;
import com.example.pxh612_loginapi_v2.viewmodel.SignupViewModel;

public class SignupActivity extends AppCompatActivity implements MyDialogFragment.MyDialogFragmentListener {



    enum SIGNUP_STATUS{
        INVALID
    }
    enum BUTTON_CLICK{
        SIGN_UP,
        SHOW_PASSWORD,
        SIGN_IN_REDIRECT
    }

    // Class
    SignupViewModel signupViewModel = new SignupViewModel();
    MyDialogFragment myDialogFragment;

    // Data
    String username;
    String password;

    // XML
    //TextView welcomeMessage;
    TextView signupButton;
    EditText usernameEditText;
    EditText passwordEditText;
    ImageView showPassword;
    TextView signinRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.v("__ pass", "SignupActivity > onCreate : setContentView(R.layout.activity_signup);");

        // XML attach
        signupButton = findViewById(R.id.signup_button);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        showPassword = findViewById(R.id.show_password);
        signinRedirect = findViewById(R.id.sign_up_small);
        //welcomeMessage = findViewById(R.id.welcome_message);

        // XML init
        //welcomeMessage.setText("Welcome, " + CurrentAccount.getDisplayName() + "!");

        // Button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButton(BUTTON_CLICK.SIGN_UP);
            }
        });
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButton(BUTTON_CLICK.SHOW_PASSWORD);
            }
        });
        signinRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButton(BUTTON_CLICK.SIGN_IN_REDIRECT);
            }
        });
    }

    private void onClickButton(BUTTON_CLICK button_click) {
        if(button_click == BUTTON_CLICK.SIGN_UP){

            notifyNotImplemented();

//
//            fetchUserInput();
//            hideInputKeyboard();
//            if(signupViewModel.signupThroughAPISuccessfully(username, password)){
//                startMainActivity();
//            }
//            else notifyInvalidAccount();
        }
        else if(button_click == BUTTON_CLICK.SHOW_PASSWORD){
            toggleShowPassword();
        }
        else if(button_click == BUTTON_CLICK.SIGN_IN_REDIRECT){
            finish();
        }
    }

    private void notifyInvalidAccount() {
        showDialogFragment(SIGNUP_STATUS.INVALID);
    }
    private void showDialogFragment(SIGNUP_STATUS status){
        Bundle bundle = new Bundle();
        MyDialogFragment.STATE state = null;
        if(status == SIGNUP_STATUS.INVALID) {
            bundle.putString("message", Messages.INVALID_DIALOG_MESSAGE);
            bundle.putString("positive_button", Messages.CLOSE_DIALOG_MESSAGE);

            state = MyDialogFragment.STATE.SIMPLE_NOTIFY;
        }

        myDialogFragment = new MyDialogFragment(state, bundle);
        myDialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    private void fetchUserInput() {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
    }
    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void hideInputKeyboard() {
        // Get a reference to the input manager
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

        // Check if the keyboard is currently visible
        View view = this.getCurrentFocus();
        if (view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    private void notifyNotImplemented() {
        String errorMessage = "Not yet implemented";
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void toggleShowPassword() {
        if (passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
    }

    @Override
    public void onDialogFragmentButtonClick(MyDialogFragment.BUTTON button) {

    }

}
