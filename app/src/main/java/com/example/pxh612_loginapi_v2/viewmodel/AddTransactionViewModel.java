package com.example.pxh612_loginapi_v2.viewmodel;

import android.content.Context;

import com.example.pxh612_loginapi_v2.repository.TransactionRepository;

public class AddTransactionViewModel {
    TransactionRepository transactionRepository;
    Context context;
    public AddTransactionViewModel(Context context) {
        this.context = context;
        transactionRepository = new TransactionRepository(context);
    }

    public void addTransaction(String category, int amount, long date) {
        transactionRepository.addTransaction(category, amount, date);
    }
}
