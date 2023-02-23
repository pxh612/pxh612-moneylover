package com.example.pxh612_loginapi_v2.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.example.pxh612_loginapi_v2.R;
import com.example.pxh612_loginapi_v2.activity.LoginActivity;
import com.example.pxh612_loginapi_v2.database.Strings;

public class MyDialogFragment extends DialogFragment {



    public enum STATE{
        LOADING,
        SIMPLE_NOTIFY
    }
    STATE state;

    // Data
    private String message;
    private String positiveButton;
    private LoginActivity.LOGIN_STATUS status;


    // XML
    ConstraintLayout constraintLayout;
    View gray_background;
    View dialogBox;
    ProgressBar progressBar;
    TextView messageTextView;
    TextView closeButton;

    // enum
//
//    public MyDialogFragment(LoginActivity.LOGIN_STATUS status, Bundle bundle) {
//        this.message = bundle.getString("message");
//        this.positiveButton = bundle.getString("positive_button");
////        this.message = message;
//        this.status = status;
//    }

    public MyDialogFragment(STATE state, Bundle bundle){
        this.message = bundle.getString("message");
        this.positiveButton = bundle.getString("positive_button");

        if(state == STATE.LOADING){
            message = Strings.LOADING_DIALOG_MESSAGE;
        }
    }

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
//        setArguments();


        // CRASHED
//        hideAll();
//        getView().setVisibility(View.VISIBLE);
//        showLoadingDialog();
//        getView().setVisibility(View.GONE);
//        getDialog().setTitle("Dialog #"); //  set DialogFragment title: crashed
    }

//    private void showEssentials(STATE state){
////        hideAll();
////        getView().setVisibility(View.GONE);
////        constraintLayout.setVisibility(View.VISIBLE);
////        getView().
////        gray_background.setVisibility(View.VISIBLE);
////        dialogBox.setVisibility(View.VISIBLE);
////        messageTextView.setVisibility(View.VISIBLE);
////        Log.d("__ pass", "MyDialogFragment > showEssentials() :  messageTextView.setVisibility(View.VISIBLE);");
////        if(state == )
//        messageTextView.setText(message);
//
//
//        if(state == STATE.LOADING){
////            messageTextView.setText("Loading...");
//            progressBar.setVisibility(View.VISIBLE);
//        }
//        else {
//            progressBar.setVisibility(View.GONE);
//        }
//
//        if(state == STATE.SIMPLE_NOTIFY){
////            getView().setVisibility(View.GONE);
////            getView().setVisibility(View.VISIBLE);
////            messageTextView.setText(message);
//            closeButton.setVisibility(View.VISIBLE);
//        }
//        else{
//            closeButton.setVisibility(View.GONE);
//        }
//    }
//

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

//    public void showLoadingDialog() {
////        Log.d("__ pass", "MyDialogFragment > showLoadingDialog");
//        this.message = "Loading...";
//        showEssentials(STATE.LOADING);
//        hideAll();
//        showEssentials();
////        message.setVisibility(View.VISIBLE);
//        progressBar.setVisibility(View.VISIBLE);
//        message.setText("Loading...");
//        getView().setVisibility(View.VISIBLE);
//        closeButton.setVisibility(View.GONE);
//    }

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

//    public void notifySimple(String message) {
////        this.messageTextView
//        this.message = message;
//        showEssentials(STATE.SIMPLE_NOTIFY);
//        Log.d("__ pass", "MyDialogFragment > notifySimple");

//        progressBar.setVisibility(View.GONE);
//        closeButton.setVisibility(View.VISIBLE);
//        this.message.setText(message);
//    }

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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create a new AlertDialog

        Log.d("__ pass", "MyDialogFragment > onCreateDialog()");
//
//        if(state == STATE.LOADING){
//            LayoutInflater inflater = requireActivity().getLayoutInflater();
//            View view = inflater.inflate(R.layout.fragment_my_dialog, null);
//            setCancelable(false);
//            Log.d("__ pass", "MyDialogFragment > onCreateDialog() : if(state == STATE.LOADING){");
//        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setMessage(message);

        if(positiveButton != null)
                builder.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Handle OK button click
                    }
                });
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        // Handle Cancel button click
//                    }
//                });

//        if(state == STATE.LOADING){
////            builder.setIcon(R.dr)
//        }

        // Return the created dialog
        return builder.create();
    }
}
