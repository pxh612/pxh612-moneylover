package com.example.pxh612_loginapi_v2.databasehelper;

import static com.example.pxh612_loginapi_v2.database.Strings.AMP;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pxh612_loginapi_v2.model.Transaction;
import com.example.pxh612_loginapi_v2.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.Calendar;

import timber.log.Timber;

public class TransactionDatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 2;
    // BIG_ERROR: cant do anything with SQLite. I changed the table's content and the table stuck with the old one. To create a brand new table, you must change DATABASE_VERSION

    private static final String TABLE_NAME = "transactiondb";
    private static final String ID_COL = "id";
    private static final String AMOUNT_COL = "amount";
    private static final String CATE_COL = "category";
    private static final String DATE_COL = "date";
    private static final String CUSTOMID_COL = "customid";

    static TransactionDatabaseHelper instance;
    private TransactionDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public static TransactionDatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new TransactionDatabaseHelper(context, TABLE_NAME, null, DATABASE_VERSION);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Timber.d("creating table");
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AMOUNT_COL + " INTEGER,"
                + CATE_COL + " TEXT,"
                + DATE_COL + " INTEGER,"
                + CUSTOMID_COL + " INTEGER" + ")";
        sqLiteDatabase.execSQL(query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Timber.d("creating table");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addTransaction(String category, int amount, long date, int customID){
        Timber.i("category & amount & date & customID = " + category + AMP + amount + AMP + date + AMP + customID);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(AMOUNT_COL, amount);
        values.put(DATE_COL, date);
        values.put(CATE_COL, category);
        values.put(CUSTOMID_COL, customID);


        db.insert(TABLE_NAME, null, values);
        Timber.d("inserted item into transaction table");
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
                long epochSeconds = cursorCourses.getInt(3);
//                Calendar calendar;
//                calendar.setTimeInMillis(epochSeconds;);
                transactions.add(new Transaction(cate, amount, epochSeconds));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        cursorCourses.close();

        Timber.i("transactions.size() = " + transactions.size());
        return transactions;
    }

    public void removeItem(int position) {

    }

    public void removeAllItem(){
        Timber.w("TODO");

        Timber.e("before reading SQLiteDatabase");
        SQLiteDatabase db = this.getWritableDatabase();
        Timber.d("after reading SQLiteDatabase");
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NAME, null, null);
//        db.close();
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


