package com.example.pxh612_loginapi_v2.viewmodel;

import static com.example.pxh612_loginapi_v2.datasource.Symbols.EQL;

import android.content.Context;

import com.example.pxh612_loginapi_v2.model.Transaction;
import com.example.pxh612_loginapi_v2.repository.TransactionRepository;

import java.util.ArrayList;

import timber.log.Timber;

public class MainViewModel {

    static TransactionRepository transactionRepository;
    Context context;

    public MainViewModel(Context context) {
        this.context = context;
//        transactionRepository = new TransactionRepository(context);
        transactionRepository = TransactionRepository.getInstance(context);
    }

    public ArrayList<Transaction> getTransactionArrayList() {
//        ArrayList<Transaction> transactions = new ArrayList<>();
//        return transactions;
//        transactions = TransactionRepository.getTransactionArrayList();
//        return transactions;
        return transactionRepository.getTransactionArrayList();
    }

    public void removeTransaction(int transactionId) {
        Timber.v("removing transaction with id" + EQL + transactionId);
        transactionRepository.removeTransaction(transactionId);
    }

    public void removeAllTransaction() {
        transactionRepository.removeAllTransaction();
    }
}
