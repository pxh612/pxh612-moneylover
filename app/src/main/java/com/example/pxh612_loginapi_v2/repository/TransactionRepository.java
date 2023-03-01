package com.example.pxh612_loginapi_v2.repository;

import android.content.Context;
import android.util.Log;

import com.example.pxh612_loginapi_v2.model.Transaction;

import java.util.ArrayList;

public class TransactionRepository {

    TransactionDatabaseHelper transactionDatabaseHelper;
    static ArrayList<Transaction> transactionArrayList = new ArrayList<>();
    Context context;

    public TransactionRepository(Context context) {
        this.context = context;
        transactionDatabaseHelper = new TransactionDatabaseHelper(context, "transactiondb", null, 1);
//        currentAcountDatabaseHelper = new CurrentAcountDatabaseHelper(context, "accountdb", null, 1);
    }

    public ArrayList<Transaction> getTransactionArrayList() {
//        transactionArrayList.clear();
//
////        transactionArrayList = new ArrayList<>();
//        transactionArrayList.add(new Transaction("food", 555000, 2022));
//        transactionArrayList.add(new Transaction("gas", 100000, 2022));
//        transactionArrayList.add(new Transaction("clothes", 1500000, 2023));
//        transactionArrayList.add(new Transaction("internet", 120000, 2023));

        transactionArrayList = transactionDatabaseHelper.getTransactionArrayList();
        Log.v("__ pass",
                "TransactionRepository > getTransactionArrayList() : " +
                        " transactionArrayList.size() = " + Integer.toString(transactionArrayList.size()));

        return transactionArrayList;
    }

    public void addTransaction(String category, int amount, long date) {
        transactionDatabaseHelper.addTransaction(category, amount, date);
    }
}
