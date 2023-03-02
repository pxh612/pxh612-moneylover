package com.example.pxh612_loginapi_v2.databasehelper;

import static com.example.pxh612_loginapi_v2.datasource.Symbols.AMP;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import timber.log.Timber;

public class KeyValueDatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "keydb";
    private static final String ID_COL = "id";
    private static final String KEYNAME_COL = "keyname";
    private static final String KEYVALUE_COL = "keyvalue";
    private static final int DATABASE_VERSION = 2;

    private static KeyValueDatabaseHelper instance;
    private KeyValueDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static KeyValueDatabaseHelper getInstance(Context context) {
        if(instance == null){
            instance = new KeyValueDatabaseHelper(context, TABLE_NAME, null, DATABASE_VERSION);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Timber.d("test timber");
//        Log.v("myLog - Pass first line", "KeyValueDatabaseHelper > onCreate();");
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEYNAME_COL + " TEXT" + ","
                + KEYVALUE_COL + " TEXT" + ")";

//                + USERNAME_COL + " TEXT,"
//                + IS_LOGGED_IN + " INTEGER)";
        sqLiteDatabase.execSQL(query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Timber.v("test timber");

//        Log.v("myLog - Pass first line", "KeyValueDatabaseHelper > onUpgrade");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

//    public void addAccount(String username){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        // on below line we are passing all values
//        // along with its key and value pair.
//        values.put(KEYNAME_COL, username);
//
//
//        db.insert(TABLE_NAME, null, values);
//        db.close();
//    }
    public void insertKeyValue(String keyName, String keyValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(KEYNAME_COL, keyName);
        values.put(KEYVALUE_COL, keyValue);


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String getKeyValue(String keyNameSearched) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses
                = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        String keyValueResult = null;
        if (cursorCourses.moveToFirst()) {
            do {
               String keyNameFetched  = cursorCourses.getString(1);
               String keyValueFetched = cursorCourses.getString(2);
               if(keyNameFetched.equals(keyNameSearched)) keyValueResult = keyValueFetched;
               Timber.v("cursorCourses moving: keynameFetched & keyValueFetched = " + keyNameFetched + AMP + keyValueFetched);
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return keyValueResult;
    }
//
//    public void deleteAccount() {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // Delete all rows from the table
////        db.delete(TABLE_NAME, null, null);
//
//        // Close the database connection
//        db.close();
//
//    }

    public void deleteKeyValue(String keyNameSearched) {
        SQLiteDatabase db = this.getWritableDatabase();

        // The SQLite DELETE statement allows you to delete one row, multiple rows, and all rows in a table. The syntax of the SQLite DELETE statement is as follows:
        //  DELETE FROM table
        //  WHERE search_condition;

        //     public int delete(String table, String whereClause, String[] whereArgs) {
        db.delete(TABLE_NAME, KEYNAME_COL + " = " + "\"" + keyNameSearched + "\"", null);

        db.close();
    }

    public void updateOrInsertKeyValue(String keyName, String keyValue) {
//        Suppose, Jane got married and she wanted to change her last name to her husband’s last name i.e., Smith. In this case, you can update Jane’s last name using the following statement:
//        UPDATE employees
//        SET lastname = 'Smith'
//        WHERE employeeid = 3;

        ContentValues values = new ContentValues();
        values.put(KEYNAME_COL, keyName);
        values.put(KEYVALUE_COL, keyValue);

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEYNAME_COL + "=?", new String[] { keyName }, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();

        if (exists) {
            // If the key name exists, update the existing row
            db.update(TABLE_NAME, values, KEYNAME_COL + "=?", new String[]{keyName});

        } else {
            // If the key name does not exist, insert a new row

            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }

//    public void updateRow(int rowId, ContactModel contactObj) {
//        ContentValues values = prepareData(contactObj);
//        String whereClause = TABLE_ROW_ID + "=?";
//        String whereArgs[] = new String[] {String.valueOf(rowId)};
//        db.update(TABLE_NAME, values, whereClause, whereArgs);
//    }
}
