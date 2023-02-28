package com.example.pxh612_loginapi_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pxh612_loginapi_v2.R;
import com.example.pxh612_loginapi_v2.viewmodel.AddTransactionViewModel;

public class AddTransactionActivty extends AppCompatActivity {

    enum BUTTON_CLICK{
        SUBMIT
    }

    // XML
    EditText categoryEditText;
    EditText amountEditText;
    EditText dateEditText;
    ImageView submitButton;

    // Data
    String category = null;
    int amount = 0;
    int date = 0;

    // Class
    AddTransactionViewModel addTransactionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction_activty);

        // Class: new pointer
        addTransactionViewModel = new AddTransactionViewModel(this);

        // XML attach
        categoryEditText = findViewById(R.id.category_edittext);
        amountEditText = findViewById(R.id.amount_edittext);
        dateEditText = findViewById(R.id.date_edittext);
        submitButton = findViewById(R.id.submit);

        // Button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onClickButton(BUTTON_CLICK.SUBMIT);}
        });
    }

    private void onClickButton(BUTTON_CLICK button_click) {
        if(button_click == BUTTON_CLICK.SUBMIT){
            fetchUserInput();
            addTransactionViewModel.addTransaction(category, amount, date);
            finish();
        }
    }

    protected void fetchUserInput(){

        // Careful: NumberFormatException

        category = categoryEditText.getText().toString();
        amount = Integer.parseInt( (String) amountEditText.getText().toString() );
        date = Integer.parseInt( (String) dateEditText.getText().toString() );
        Log.v("__ Values:", "AddTransactionActivty > fetchUserInput : category & amount & date" +
                category + " & " + Integer.toString(amount) + " & " + Integer.toString(date));
    }
}