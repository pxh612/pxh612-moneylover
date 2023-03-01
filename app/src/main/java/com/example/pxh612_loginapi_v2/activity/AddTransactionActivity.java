package com.example.pxh612_loginapi_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pxh612_loginapi_v2.R;
import com.example.pxh612_loginapi_v2.viewmodel.AddTransactionViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTransactionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{



    enum BUTTON_CLICK{
        SUBMIT,
        DATE_EDIT
    }

    // XML
    EditText categoryEditText;
    EditText amountEditText;
    EditText dateEditText;
    ImageView submitButton;
    TextView date_display;
    DatePickerDialog datePickerDialog;

    // Data
    String category = null;
    int amount = 0;
    int date = 0;
    Calendar calendar = null;
    long epochSeconds;


    // Class
    AddTransactionViewModel addTransactionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction_activty);

        // Class: new pointer
        addTransactionViewModel = new AddTransactionViewModel(this);

        // Data: init
        calendar = Calendar.getInstance();
        epochSeconds = calendar.getTimeInMillis()/1000;

        // XML attach
        categoryEditText = findViewById(R.id.category_edittext);
        amountEditText = findViewById(R.id.amount_edittext);
//        dateEditText = findViewById(R.id.date_edittext);
        submitButton = findViewById(R.id.submit);
        date_display = findViewById(R.id.date_display);

        // XML Button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onClickButton(BUTTON_CLICK.SUBMIT);}
        });
        date_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onClickButton(BUTTON_CLICK.DATE_EDIT);}
        });

        // XML init
        displayDate(calendar);
    }
    @Override
    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay){
        Log.v("__ pass", "AddTransactionActivty > onDateSet : selectedDay = " + Integer.toString(selectedDay));

        String day = Integer.toString(selectedDay);
        String year = Integer.toString(selectedYear);

        String display = day + " - " + year;
        date_display.setText(display);
    }

    private void onClickButton(BUTTON_CLICK button_click) {
        if(button_click == BUTTON_CLICK.SUBMIT){
            fetchUserInput();
            addTransactionViewModel.addTransaction(category, amount, epochSeconds);
            finish();
        }
        else if(button_click == BUTTON_CLICK.DATE_EDIT){
            editDate();
        }
    }

    private void editDate() {
        //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                datePickerDialog = new DatePickerDialog(AddTransactionActivity.this);
//            }
//            Calendar calendar = Calendar.getInstance();
//            if(calendar == null) calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay){
                Log.v("__ pass", "AddTransactionActivty > onDateSet : selectedDay = " + Integer.toString(selectedDay));

                calendar.set(selectedYear, selectedMonth, selectedDay);
                epochSeconds = calendar.getTimeInMillis()/1000;

                displayDate(calendar);

                Log.v("__ pass", "AddTransactionActivty > onDateSet : epochSeconds = " + Long.toString(epochSeconds));
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void displayDate(Calendar calendar) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDateTime = sdf.format(calendar.getTime());
        String display = formattedDateTime;

        date_display.setText(display);
    }


    protected void fetchUserInput(){

        // Careful: NumberFormatException

        category = categoryEditText.getText().toString();
        amountEditText.getText().toString();
//        amount = Integer.parseInt( (String) amountEditText.getText().toString() );
//        date = Integer.parseInt( (String) dateEditText.getText().toString() );
//        Log.v("__ Values:", "AddTransactionActivty > fetchUserInput : category & amount & date = " +
//                category + " & " + Integer.toString(amount) + " & " + Integer.toString(date));
    }
}