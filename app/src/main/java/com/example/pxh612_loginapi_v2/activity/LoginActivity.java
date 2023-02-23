package com.example.pxh612_loginapi_v2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pxh612_loginapi_v2.LoginAsyncTaskListener;
import com.example.pxh612_loginapi_v2.R;
import com.example.pxh612_loginapi_v2.database.AccountExample;
import com.example.pxh612_loginapi_v2.fragment.MyDialogFragment;
import com.example.pxh612_loginapi_v2.viewmodel.LoginViewModel;


public class LoginActivity extends AppCompatActivity implements LoginAsyncTaskListener {

    // enum
    public enum LOGIN_STATUS{
        SUCCESSFUL,
        INVALID,
        NO_CONNECTION
    }

    // Dialog message
    private final String INVALID_DIALOG_MESSAGE = "" +
            "Invalid email/password combination." + "\n" +
            "Please try again.";
    private final String INVALID_DIALOG_POSITIVE_BUTTON = "CLOSE";
    public static final String NO_CONNECTION_DIALOG_MESSAGE = "";

    // Class
    LoginViewModel loginViewModel = new LoginViewModel();
//    MyDialogFragment myDialogFragment = new MyDialogFragment();

    // Fragment
    FragmentTransaction fragmentTransaction;

    // XML

    EditText usernameEditText;
    EditText passwordEditText;
    TextView loginButton;
    TextView connectWithGoogleButton;
    ImageView showPassword;
    Button signupButton;
    ProgressBar progressBar;
    ConstraintLayout activityLoginLayout;
    ConstraintLayout loadingLayout;

    // Data
    String username;
    String password;

    // Pretype
    String usernamePretype = AccountExample.email;
    String passwordPretype = AccountExample.password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // XML: attach
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup);
        connectWithGoogleButton = findViewById(R.id.connect_to_google_button);
        showPassword = findViewById(R.id.show_password);
//        progressBar = findViewById(R.id.progressbar_cyclic);
        loadingLayout = findViewById(R.id.fragment_dialog);
        activityLoginLayout = findViewById(R.id.activity_login_layout);


        // Fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_dialog, myDialogFragment);
//        fragmentTransaction.hide(myDialogFragment);
//        fragmentTransaction.commit();


        // Intialize XML
        usernameEditText.setText(usernamePretype);
        passwordEditText.setText(passwordPretype);
//        activityLoginLayout.setClickable(false);
//        myDialogFragment.hideAll();
//        loadingLayout.setVisibility(View.VISIBLE);
//        loadingLayout.setVisibility(View.GONE);
//        myDialogFragment.hideAll();

        // Button click
        onCreateButtonClick();
    }

    private void onCreateButtonClick() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fetchUserInput();
                loginViewModel.gainAccessToServer(LoginActivity.this, username, password);

                hideInputKeyboard();
                showLoadingScreen();

//                showMyDialogFragment();
//                myDialogFragment.showLoadingDialog();
//                Bundle bundle = new Bundle();
//                bundle.putString("message", INVALID_DIALOG_MESSAGE);
//                bundle.putString("positive_button", INVALID_DIALOG_POSITIVE_BUTTON);
//                MyDialogFragment myDialogFragment = new MyDialogFragment(LOGIN_STATUS.INVALID, bundle);
//                myDialogFragment.show(getSupportFragmentManager(), "dialog");
//                loadingLayout.setVisibility(View.VISIBLE);

            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignupActivity();
            }
        });
        connectWithGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(loginViewModel.connectToGoogleAccount()){
//                    if(CurrentAccount.isLoggedIn()) startMainActivity();
//                }
                notifyNotImplemented();
            }
        });
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleShowPassword();
            }
        });
    }

    private void showLoadingScreen() {
        //TODO
    }

    private void notifyInvalidLogin(){
        Bundle bundle = new Bundle();
        bundle.putString("message", INVALID_DIALOG_MESSAGE);
        bundle.putString("positive_button", INVALID_DIALOG_POSITIVE_BUTTON);
        MyDialogFragment myDialogFragment = new MyDialogFragment(LOGIN_STATUS.INVALID, bundle);
        myDialogFragment.show(getSupportFragmentManager(), "dialog");
    }
//
//    private void showMyDialogFragment() {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.show(myDialogFragment).commit();
//        Log.d("__ pass", "LoginActivity > showMyDialogFragment");
//
//        activityLoginLayout.setClickable(false);
////        fragmentTransaction.commit();
////        myDialogFragment.showLoadingDialog();
//    }
//    private void hideMyDialogFragment() {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.hide(myDialogFragment).commit();
//
//        activityLoginLayout.setClickable(false);
//    }


    @Override
    public void onLoginAsyncTaskPostExecute(LOGIN_STATUS result) {
        Log.d("__ pass", "LoginActivity > onLoginAsyncTaskPostExecute");
        if(result == LOGIN_STATUS.SUCCESSFUL){
//            myDialogFragment.hideAll();
//            hideMyDialogFragment();
            startMainActivity();
        }
        else if(result == LOGIN_STATUS.INVALID){
//            myDialogFragment.notifyLoginInvalid();
//            showMyDialogFragment();
//            myDialogFragment.notifySimple(INVALID_DIALOG_MESSAGE);
//            Log.d("__ pass", "LoginActivity > onLoginAsyncTaskPostExecute :  else if(result == LOGIN_STATUS.INVALID){ ");
            notifyInvalidLogin();
        }
        else if(result == LOGIN_STATUS.NO_CONNECTION){
//            myDialogFragment.notifyNoConnection();
//            myDialogFragment.notifySimple(NO_CONNECTION_DIALOG_MESSAGE);
        }
    }


//    private void showLoadingDialog() {
//        //progressBar.setVisibility(View.VISIBLE);
//        loadingLayout.setVisibility(View.VISIBLE);
//    }


    private void toggleShowPassword() {
        if (passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
    }

    private void notifyNotImplemented() {
        String errorMessage = "Not yet implemented";
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void startSignupActivity() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    private void notifyLoginInvalid() {
        String errorMessage = "Incorrect username or password";
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void fetchUserInput() {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
    }

    @Override
    public void onBackPressed() {
//        hideInputKeyboard();
//        hideMyDialogFragment();
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

//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if(isPreviewMode())
//        {
//            //No child is clickable in preview mode.
//            return true;
//        }
//
//        //All children are clickable otherwise
//        return false;
//    }
}