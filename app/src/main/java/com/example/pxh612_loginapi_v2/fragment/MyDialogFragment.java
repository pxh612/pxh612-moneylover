package com.example.pxh612_loginapi_v2.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.example.pxh612_loginapi_v2.R;
import com.example.pxh612_loginapi_v2.activity.LoginActivity;

public class MyDialogFragment extends DialogFragment {



    enum STATE{
        LOADING,
        SIMPLE_NOTIFY
    }

    // Data
    private String message;

    // XML
    ConstraintLayout constraintLayout;
    View gray_background;
    View dialogBox;
    ProgressBar progressBar;
    TextView messageTextView;
    TextView closeButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_dialog, container, false);


        // Init XML
//        hideAll();

        return v;
    }

    @Override
    public void onViewCreated(View v,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Log.d("__ pass", "MyDialogFragment > onViewCreated");

        // XML attach
        constraintLayout = v.findViewById(R.id.constraint_layout);
        dialogBox = v.findViewById(R.id.dialog_box);
        progressBar = v.findViewById(R.id.progressbar_cyclic);
        messageTextView = v.findViewById(R.id.message);
        closeButton = v.findViewById(R.id.close_button);
        gray_background = v.findViewById(R.id.gray_background);
        Log.d("__ pass", "MyDialogFragment > onCreateView");

        // init XML
//        message.setText();


        // CRASHED
//        hideAll();
//        getView().setVisibility(View.VISIBLE);
//        showLoadingDialog();
//        getView().setVisibility(View.GONE);
//        getDialog().setTitle("Dialog #"); //  set DialogFragment title: crashed
    }

    private void showEssentials(STATE state){
//        hideAll();
//        getView().setVisibility(View.GONE);
//        constraintLayout.setVisibility(View.VISIBLE);
//        getView().
//        gray_background.setVisibility(View.VISIBLE);
//        dialogBox.setVisibility(View.VISIBLE);
//        messageTextView.setVisibility(View.VISIBLE);
//        Log.d("__ pass", "MyDialogFragment > showEssentials() :  messageTextView.setVisibility(View.VISIBLE);");
//        if(state == )
        messageTextView.setText(message);


        if(state == STATE.LOADING){
            messageTextView.setText("Loading...");
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }

        if(state == STATE.SIMPLE_NOTIFY){
//            getView().setVisibility(View.GONE);
//            getView().setVisibility(View.VISIBLE);
//            messageTextView.setText(message);
            closeButton.setVisibility(View.VISIBLE);
        }
        else{
            closeButton.setVisibility(View.GONE);
        }
    }


//    public void notifyNoConnection() {
//    }

//    public void hideLoadingDialog() {
//    }

//    public void notifyLoginInvalid() {
//        Log.d("__ pass", "MyDialogFragment > notifyLoginInvalid");
//        progressBar.setVisibility(View.GONE);
//        messageTextView.setText(LoginActivity.INVALID_DIALOG_MESSAGE);
//        hideAll();
//        getDialog().setTitle("sds");
//    }

    public void showLoadingDialog() {
        Log.d("__ pass", "MyDialogFragment > showLoadingDialog");
        showEssentials(STATE.LOADING);
//        hideAll();
//        showEssentials();
////        message.setVisibility(View.VISIBLE);
//        progressBar.setVisibility(View.VISIBLE);
//        message.setText("Loading...");
//        getView().setVisibility(View.VISIBLE);
//        closeButton.setVisibility(View.GONE);
    }

//    public void hideAll() {
//
//        Log.d("__ pass", "MyDialogFragment > hideAll");
//
//        //
//        getView().setVisibility(View.GONE);
//
////        getView().setVisibility(View.GONE);
//    //    constraintLayout.setVisibility(View.GONE);
////        view.setVisibility(View.GONE);
////        progressBar.setVisibility(View.GONE);
////        message.setVisibility(View.GONE);
//
//
//    }

    public void notifySimple(String message) {
//        this.messageTextView
        this.message = message;
        showEssentials(STATE.SIMPLE_NOTIFY);
        Log.d("__ pass", "MyDialogFragment > notifySimple");

//        progressBar.setVisibility(View.GONE);
//        closeButton.setVisibility(View.VISIBLE);
//        this.message.setText(message);
    }

//    @Override
//    public void onBackPressed(){
//        super.onBackPressed();
//    }

//    @Override
//    public void onBackPressed() {
//        // Check if there are any fragments in the back stack
//        if (getParentFragmentManager().getBackStackEntryCount() > 0) {
//            // Pop the top fragment from the back stack
//            getParentFragmentManager().popBackStack();
////        } else {
////            // If there are no fragments in the back stack, let the activity handle the back button press
////            super.onBackPressed();
//        }
//    }

}
