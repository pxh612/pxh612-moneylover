package com.example.pxh612_loginapi_v2.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pxh612_loginapi_v2.model.Transaction;

import java.util.ArrayList;

public class TransactionDatabaseHelper extends SQLiteOpenHelper{

    private static final String TABLE_NAME = "transactiondb";
    private static final String ID_COL = "id";
    private static final String AMOUNT_COL = "amount";
    private static final String CATE_COL = "category";
    private static final String DATE_COL = "date";

    public TransactionDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AMOUNT_COL + " INTEGER,"
                + CATE_COL + " TEXT,"
                + DATE_COL + " INTEGER)";
        sqLiteDatabase.execSQL(query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addTransaction(String category, int amount, int date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(AMOUNT_COL, amount);
        values.put(DATE_COL, date);
        values.put(CATE_COL, category);


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Transaction> getTransactionArrayList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses
                = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<Transaction> transactions = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                int amount = cursorCourses.getInt(1);
                String cate = cursorCourses.getString(2);
                int date = cursorCourses.getInt(3);
                transactions.add(new Transaction(cate, amount, date));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        cursorCourses.close();
        return transactions;
    }


//    public void deleteAccount() {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // Delete all rows from the table
//        db.delete(TABLE_NAME, null, null);
//
//        // Close the database connection
//        db.close();
//
//    }
}


