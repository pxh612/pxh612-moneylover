package com.example.pxh612_loginapi_v2.activity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pxh612_loginapi_v2.repository.CurrentAccount;
import com.example.pxh612_loginapi_v2.R;
import com.example.pxh612_loginapi_v2.database.Strings;
import com.example.pxh612_loginapi_v2.fragment.MyDialogFragment;
import com.example.pxh612_loginapi_v2.view.SwipeToDeleteCallback;
import com.example.pxh612_loginapi_v2.view.TransactionRecyclerViewAdapter;
import com.example.pxh612_loginapi_v2.viewmodel.MainViewModel;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MyDialogFragment.MyDialogFragmentListener, TransactionRecyclerViewAdapter.TransactionRecyclerViewAdapterListener {



    enum ACTIVITY{
        LOGIN_ACTIVITY,
        ADD_TRANSACTION_ACTIVITY
    }
    enum DIALOG_FRAGMENT{
        LOG_OUT,
        DELETE_ITEM
    }
    enum BUTTON_CLICK{
        ADD_TRANSACTION,
        LOG_OUT
    }
    final int AddTransactionActivty_REQUEST_CODE = 1;

    // Classes
    MyDialogFragment myDialogFragment;
    MainViewModel mainViewModel = new MainViewModel(this);
    TransactionRecyclerViewAdapter adapter;

    // XML
    TextView welcomeMessage;
    ImageView addButton;
    TextView logoutButton;
    RecyclerView recyclerView;

    // Data
    private int recyclerviewItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML attach
        welcomeMessage = findViewById(R.id.welcome_message);
        addButton = findViewById(R.id.add_button);
        logoutButton = findViewById(R.id.log_out);
        recyclerView = findViewById(R.id.recycler_view);

        // XML recycleView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initHandleSwipeEvent();
        updateRecycleView();

        // XML init
        welcomeMessage.setText("Welcome, " + CurrentAccount.getDisplayName() + "!");

        /// XML Button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onClickButton(BUTTON_CLICK.ADD_TRANSACTION);}
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onClickButton(BUTTON_CLICK.LOG_OUT);}
        });
    }
    private void initHandleSwipeEvent() {
        SwipeToDeleteCallback swipeToDeleteCallBack = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                recyclerviewItemPosition = viewHolder.getAdapterPosition();
                if(direction == ItemTouchHelper.LEFT){
                    removeTransaction(recyclerviewItemPosition);
//                    showDialogFragment(DIALOG_FRAGMENT.DELETE_ITEM);
//                    showDeleteConfirmationDialog(position);
                }
                Timber.d("swiped");
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onRecycleViewItemClick() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AddTransactionActivty_REQUEST_CODE) {
            updateRecycleView();
        }
    }

    @Override
    public void onBackPressed() {

    }


    private void showDialogFragment(DIALOG_FRAGMENT dialog) {
        Bundle bundle = new Bundle();
        if(dialog == DIALOG_FRAGMENT.LOG_OUT){
            bundle.putString("message", Strings.LOG_OUT_CONFIRMATION);
            bundle.putString("positive_button", Strings.YES);
            bundle.putString("negative_button", Strings.NO);
        }
        else if(dialog == DIALOG_FRAGMENT.DELETE_ITEM){
//            bundle.putString("message", Strings.DELETE_TRANSACTION_CONFIRMATION);
            bundle.putString("positive_button", Strings.YES);
            bundle.putString("negative_button", Strings.NO);
            bundle.putInt("position", recyclerviewItemPosition);
        }
        myDialogFragment = new MyDialogFragment(MyDialogFragment.STATE.SIMPLE_NOTIFY, bundle);
        myDialogFragment.show(getSupportFragmentManager(), "dialog");
    }

//    private void startAddTransactionActivity() {
//        Intent intent = new Intent(this, AddTransactionActivty.class);
//        startActivityForResult(intent, AddTransactionActivty_REQUEST_CODE);
//    }

    private void beginActivity(ACTIVITY activity) {
        if(activity == ACTIVITY.LOGIN_ACTIVITY) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else if(activity == ACTIVITY.ADD_TRANSACTION_ACTIVITY){
            Intent intent = new Intent(this, AddTransactionActivity.class);
            startActivityForResult(intent, AddTransactionActivty_REQUEST_CODE);
        }
    }

    public void onDialogFragmentButtonClick(MyDialogFragment.BUTTON button){
        Log.v("__ pass", "MainActivity > onPositiveButtonClick()");
        if(button == MyDialogFragment.BUTTON.POSITIVE_BUTTON) {
            CurrentAccount.reset();
            beginActivity(ACTIVITY.LOGIN_ACTIVITY);
        }
    }



    private void removeTransaction(int position) {
        // erase data
        // TODO main

//        mainViewModel.removeTransaction(position);
//        mainViewModel.removeTransaction();

        // update view
//        adapter.notifyItemRemoved(position);

    }

    private void updateRecycleView() {
        adapter = new TransactionRecyclerViewAdapter(mainViewModel.getTransactionArrayList(),this);
        recyclerView.setAdapter(adapter);
//        recyclerView.setAdapter(new TransactionRecyclerViewAdapter(mainViewModel.getTransactionArrayList(),this));
    }

    private void onClickButton(BUTTON_CLICK button_click) {
        if(button_click == BUTTON_CLICK.ADD_TRANSACTION){
            beginActivity(ACTIVITY.ADD_TRANSACTION_ACTIVITY);
        }
        else if(button_click == BUTTON_CLICK.LOG_OUT){
            showDialogFragment(DIALOG_FRAGMENT.LOG_OUT);
        }
    }






}