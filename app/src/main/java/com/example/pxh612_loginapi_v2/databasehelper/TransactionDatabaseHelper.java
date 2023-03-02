package com.example.pxh612_loginapi_v2.databasehelper;

import static com.example.pxh612_loginapi_v2.datasource.Symbols.AMP;
import static com.example.pxh612_loginapi_v2.datasource.Symbols.EQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pxh612_loginapi_v2.model.Transaction;

import java.util.ArrayList;

import timber.log.Timber;

public class TransactionDatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 2;
    // BIG_ERROR: cant do anything with SQLite. I changed the table's content and the table stuck with the old one. To create a brand new table, you must change DATABASE_VERSION

    private static final String TABLE_NAME = "transactiondb";
    private static final String ID_COL = "id";
    private static final String AMOUNT_COL = "amount";
    private static final int AMOUNT_ORDER = 1;
    private static final String CATE_COL = "category";
    private static final int CATE_ORDER = 2;
    private static final String DATE_COL = "date";
    private static final int DATE_ORDER = 3;
    private static final String CUSTOMID_COL = "customid";
    private static final int CUSTOMID_ORDER = 4;

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
        Timber.v("inserted item into transaction table");
        db.close();
    }

    public ArrayList<Transaction> getTransactionArrayList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses
                = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<Transaction> transactions = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                int amount = cursorCourses.getInt(AMOUNT_ORDER);
                String cate = cursorCourses.getString(CATE_ORDER);
                long epochSeconds = cursorCourses.getInt(DATE_ORDER);
                int id = cursorCourses.getInt(CUSTOMID_ORDER);
//                Calendar calendar;
//                calendar.setTimeInMillis(epochSeconds;);
                transactions.add(new Transaction(cate, amount, epochSeconds, id));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        cursorCourses.close();

        Timber.d("transactions.size() = " + transactions.size());
        return transactions;
    }

    public void removeItemWithCustomId(int id) {
        Timber.v("removing transaction with id" + EQL + id);
        SQLiteDatabase db = this.getWritableDatabase();

            // The SQLite DELETE statement allows you to delete one row, multiple rows, and all rows in a table. The syntax of the SQLite DELETE statement is as follows:
            //  DELETE FROM table
            //  WHERE search_condition;

            //     public int delete(String table, String whereClause, String[] whereArgs) {
        int deleteResult = db.delete(TABLE_NAME, CUSTOMID_COL + EQL + id, null);
        db.close();

        Timber.v("deleteResult" + EQL + deleteResult);
    }

    public void removeAllItem(){

        Timber.v("before reading SQLiteDatabase");
        SQLiteDatabase db = this.getWritableDatabase();
        Timber.v("after reading SQLiteDatabase");
        int deleteResult = db.delete(TABLE_NAME, null, null);
        db.close();

        Timber.d("deleteResult" + EQL + deleteResult);
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


