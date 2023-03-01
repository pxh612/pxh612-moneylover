package com.example.pxh612_loginapi_v2.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.pxh612_loginapi_v2.repository.CurrentAccount;
import com.example.pxh612_loginapi_v2.R;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import timber.log.Timber;


public class SplashScreenActivity extends AppCompatActivity {

    CurrentAccount currentAccount = new CurrentAccount(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        Timber.plant(new pxh612DebugTree());
        Timber.plant(new pxh612DebugTree());


        // Redirection
        if(currentAccount.isCheckedNotLoggedIn()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        ;
    }


    private class pxh612DebugTree extends Timber.Tree {
        private static final int CLASS_NAME_FINAL_LENGTH = 30;
        private static final int FUNCTION_NAME_FINAL_LENGTH = 25;
        private static final int LINE_REDIRECTION_FINAL_LENGTH = 40;

        private List<String> fqcnIgnore = Arrays.asList(
                Timber.class.getName(),
                Timber.Forest.class.getName(),
                Timber.Tree.class.getName(),
                pxh612DebugTree.class.getName()
        );

        @Override
        protected void log(int priority , @Nullable String tag, @Nullable String message, @Nullable Throwable throwable) {
            String mTag = null;
            String mMessage = null;
//            String foreword = "mMessage: ";
            String tagForeword = "pxh612 - ";

            String folderName = null;
            String className = null;
            String functionName = null;
            String fileName = null;
            int lineNumber;

            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            StackTraceElement element = null;
            for (StackTraceElement elementIterator : stackTrace) {
                if (!fqcnIgnore.contains(elementIterator.getClassName())) {
                    element = elementIterator;
                    break;
                }
            }

            String fullClassName = element.getClassName();
            int lastIndex = fullClassName.lastIndexOf('.');
            int secondLastIndex = fullClassName.lastIndexOf('.', lastIndex - 1);
            className = fullClassName.substring(lastIndex+1);
            folderName = fullClassName.substring(secondLastIndex+1,lastIndex);
            functionName = element.getMethodName();
            lineNumber = element.getLineNumber();
            fileName = element.getFileName();

            String lineRedirection = String.format("(%s:%d)",fileName,lineNumber);
            lineRedirection = String.format("%1$" + LINE_REDIRECTION_FINAL_LENGTH + "s", lineRedirection);
            // String paddedStr = String.format("%1$10s", str).replace(' ', ' ');
            // String paddedStr = String.format("%1$-10s", str).replace(' ', ' ');
            className = String.format("%1$" + CLASS_NAME_FINAL_LENGTH + "s", className).replace(' ', '_');
            functionName = String.format("%1$" + FUNCTION_NAME_FINAL_LENGTH + "s", functionName).replace(' ', '_');

            mTag = tagForeword + folderName;
//            mMessage=String.format("%s > %s (%s:%d):    %s", className, functionName, fileName, lineNumber, message);
            mMessage=String.format("%s > %s %s:    %s", className, functionName, lineRedirection, message);
//            Log.d(mTag, mMessage);
            Log.println(priority, mTag, mMessage);

        }
    }

}