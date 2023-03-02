package com.example.pxh612_loginapi_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

import com.example.pxh612_loginapi_v2.R;
import com.example.pxh612_loginapi_v2.view.CustomNumberKeyboard;

public class AddTransactionAmountActivity extends AppCompatActivity {

    // XML
    EditText editText;
    CustomNumberKeyboard keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction_amount);


        // XML attach
        editText = findViewById(R.id.editText);
        keyboard = findViewById(R.id.keyboard);

        // XML init
        initEditTectConnectionToCustomKeyboard();
    }

    private void initEditTectConnectionToCustomKeyboard() {
        // prevent system keyboard from appearing when EditText is tapped
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);

        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
    }
}