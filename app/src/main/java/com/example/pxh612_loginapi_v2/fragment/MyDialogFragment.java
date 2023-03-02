package com.example.pxh612_loginapi_v2.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import com.example.pxh612_loginapi_v2.datasource.Messages;

public class MyDialogFragment extends DialogFragment {


    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        
    }

    public enum STATE{
        LOADING,
        SIMPLE_NOTIFY
    }
    public enum BUTTON{
        NEGATIVE_BUTTON,
        POSITIVE_BUTTON
    }
    STATE state;

    // Data
    private String message;
    private String positiveButton;
    private String negativeButton;

    // Listener
    MyDialogFragmentListener listener;
    public interface MyDialogFragmentListener{
        void onDialogFragmentButtonClick(BUTTON button);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listener = (MyDialogFragmentListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " : " + " You must implement MyDialogFragmentListener");
        }
    }

    // XML
    ConstraintLayout constraintLayout;
    View gray_background;
    View dialogBox;
    ProgressBar progressBar;
    TextView messageTextView;
    TextView closeButton;




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
        this.negativeButton = bundle.getString("negative_button");

        if(state == STATE.LOADING){
            message = Messages.LOADING_DIALOG_MESSAGE;
        }
    }


//    public MyDialogFragment(STATE state, Bundle bundle, MyDialogFragmentListener listener){
//
//        this.listener = listener;
//    }
//


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_dialog, container, false);
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
        progressBar.setVisibility(View.VISIBLE);

    }

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
        builder.setMessage(message);

        if(positiveButton != null)
                builder.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDialogFragmentButtonClick(BUTTON.POSITIVE_BUTTON);
                    }
                });

        if(negativeButton != null)
                builder.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDialogFragmentButtonClick(BUTTON.NEGATIVE_BUTTON);
                    }
                });

//        if(state == STATE.LOADING){
////            builder.setIcon(R.dr)
//        }

        // Return the created dialog
        return builder.create();
    }
}
