package com.example.pxh612_loginapi_v2.activity;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pxh612_loginapi_v2.CurrentAccount;
import com.example.pxh612_loginapi_v2.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    enum BUTTON_CLICK{
        ADD_TRANSACTION
    }
    final int AddTransactionActivty_REQUEST_CODE = 1;


    // XML
    TextView welcomeMessage;
    ImageView addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML attach
        welcomeMessage = findViewById(R.id.welcome_message);
        addButton = findViewById(R.id.add_button);

        // XML init
        welcomeMessage.setText("Welcome, " + CurrentAccount.getDisplayName() + "!");

        /// Button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onClickButton(BUTTON_CLICK.ADD_TRANSACTION);}
        });
    }

    private void onClickButton(BUTTON_CLICK button_click) {
        if(button_click == BUTTON_CLICK.ADD_TRANSACTION){
            startAddTransactionActivity();
        }
    }

    private void startAddTransactionActivity() {
        Intent intent = new Intent(this, AddTransactionActivty.class);
        startActivityForResult(intent, AddTransactionActivty_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AddTransactionActivty_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                updateView();
            }
        }
    }

    private void updateView() {
        // TODO
    }
}