package com.example.pxh612_loginapi_v2.repository;

import static com.example.pxh612_loginapi_v2.datasource.Symbols.EQL;

import android.content.Context;

import com.example.pxh612_loginapi_v2.datasource.KeyNames;
import com.example.pxh612_loginapi_v2.databasehelper.KeyValueDatabaseHelper;
import com.example.pxh612_loginapi_v2.databasehelper.TransactionDatabaseHelper;
import com.example.pxh612_loginapi_v2.model.Transaction;

import java.util.ArrayList;

import timber.log.Timber;

public class TransactionRepository {

    // Data
    static int customTransactionIdAssigner;
    static ArrayList<Transaction> transactionArrayList = new ArrayList<>();

    // Classes
    static TransactionDatabaseHelper transactionDatabaseHelper;
    static KeyValueDatabaseHelper keyValueDatabaseHelper;
    Context context;

    // SingleTon
    private static TransactionRepository instance;


    private TransactionRepository(Context context) {
        this.context = context;
        keyValueDatabaseHelper = KeyValueDatabaseHelper.getInstance(context);
//        transactionDatabaseHelper = new TransactionDatabaseHelper(context, "transactiondb", null, 1);
        transactionDatabaseHelper = TransactionDatabaseHelper.getInstance(context);

//        Timber.e("transactionDatabaseHelper.removeAllItem(); = CRASHED");
//        Timber.e("transactionDatabaseHelper.addTransaction(\"cooking\",555,3423423,99);");
       // transactionDatabaseHelper.removeAllItem(); // CRASHED
//        keyValueDatabaseHelper.getKeyValue("CustomTransactionIDAssigner"); // CRASHED

    }
    public static TransactionRepository getInstance(Context context){
        if(instance == null){
            instance = new TransactionRepository(context);
        }
        return instance;
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
        return transactionArrayList;
    }

    public void addTransaction(String category, int amount, long date) {
        int customID = newCustomID();
        transactionDatabaseHelper.addTransaction(category, amount, date, customID);
    }

    private int newCustomID() {
        customTransactionIdAssigner = Integer.parseInt(keyValueDatabaseHelper.getKeyValue(KeyNames.CustomTransactionIDAssigner));
        Timber.v("customTransactionIdAssigner = " + customTransactionIdAssigner);
        // BIG-ERROR: I forgot to pass value directly. The function give value to nowhere.
        customTransactionIdAssigner++;


        String customTransactionIdAssignerString = Integer.toString(customTransactionIdAssigner);
        keyValueDatabaseHelper.updateOrInsertKeyValue(KeyNames.CustomTransactionIDAssigner, customTransactionIdAssignerString);

        return customTransactionIdAssigner;
    }

    public void removeTransaction(int transactionId) {
        Timber.v("removing transaction with id" + EQL + transactionId);
        transactionDatabaseHelper.removeItemWithCustomId(transactionId);
    }

    public void removeAllTransaction() {
        transactionDatabaseHelper.removeAllItem();
    }
}
