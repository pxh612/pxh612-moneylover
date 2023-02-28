package com.example.pxh612_loginapi_v2.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.pxh612_loginapi_v2.model.Transaction;
import com.example.pxh612_loginapi_v2.repository.TransactionRepository;

import java.util.ArrayList;

public class MainViewModel {

    TransactionRepository transactionRepository;
    Context context;

    public MainViewModel(Context context) {
        this.context = context;
        transactionRepository = new TransactionRepository(context);
    }

    public ArrayList<Transaction> getTransactionArrayList() {
//        ArrayList<Transaction> transactions = new ArrayList<>();
//        return transactions;
//        transactions = TransactionRepository.getTransactionArrayList();
//        return transactions;
        return transactionRepository.getTransactionArrayList();
    }

}
