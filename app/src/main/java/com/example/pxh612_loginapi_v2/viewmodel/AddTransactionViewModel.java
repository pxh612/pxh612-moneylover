package com.example.pxh612_loginapi_v2.viewmodel;

import android.content.Context;

import com.example.pxh612_loginapi_v2.repository.TransactionRepository;

import timber.log.Timber;

public class AddTransactionViewModel {
    static TransactionRepository transactionRepository;
    Context context;
    public AddTransactionViewModel(Context context) {
        this.context = context;
        transactionRepository = TransactionRepository.getInstance(context);
    }

    public void addTransaction(String category, int amount, long date) {
        Timber.v("amount & date = " + amount + " & " + date);
        transactionRepository.addTransaction(category, amount, date);
    }
}
