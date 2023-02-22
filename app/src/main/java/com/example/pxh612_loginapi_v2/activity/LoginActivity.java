package com.example.pxh612_loginapi_v2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
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

    // Login status
    public enum LOGIN_STATUS{
        SUCCESSFUL,
        INVALID,
        NO_CONNECTION
    }

    // ProjectStructure
    LoginViewModel loginViewModel = new LoginViewModel();

    // XML
    EditText usernameEditText;
    EditText passwordEditText;
    TextView loginButton;
    TextView connectWithGoogleButton;
    ImageView showPassword;
    Button signupButton;
    ProgressBar progressBar;
    ConstraintLayout loadingLayout;

    // Data
    String username;
    String password;

    // Example
    String usernamePretype = AccountExample.email;
    String passwordPretype = AccountExample.password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MyDialogFragment myDialogFragment = new MyDialogFragment();
        fragmentTransaction.add(R.id.fragment_my_dialog, myDialogFragment);

        fragmentTransaction.commit();

        // XML: attach
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup);
        connectWithGoogleButton = findViewById(R.id.connect_to_google_button);
        showPassword = findViewById(R.id.show_password);
        progressBar = findViewById(R.id.progressbar_cyclic);
        loadingLayout = findViewById(R.id.loading_layout);

        // Intialize XML
        usernameEditText.setText(usernamePretype);
        passwordEditText.setText(passwordPretype);
        loadingLayout.setVisibility(View.GONE);

        // Button click
        onCreateButtonClick();
    }

    private void onCreateButtonClick() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO

//                fetchUserInput();
//                loginViewModel.gainAccessToServer(username, password);
                showLoadingDialog();
////
//
//                if(loginViewModel.gainAccessSuccessfully(username, password)){
//                    startMainActivity();
//                }
//                else {
//                    notifyLoginError();
//                }
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

    @Override
    public void onLoginAsyncTaskPostExecute(LOGIN_STATUS result) {
        if(result == LOGIN_STATUS.SUCCESSFUL){
            hideLoadingDialog();
        }
        else if(result == LOGIN_STATUS.INVALID){
            notifyLoginInvalid();
        }
        else if(result == LOGIN_STATUS.NO_CONNECTION){
            notifyNoConnection();
        }
    }

    private void showLoadingDialog() {
        //TODO: darken background, have to implement DialogFragment
        //progressBar.setVisibility(View.VISIBLE);
        loadingLayout.setVisibility(View.VISIBLE);
    }


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
    }
}