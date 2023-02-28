package com.example.pxh612_loginapi_v2.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CurrentAcountDatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "accountdb";
    private static final String ID_COL = "id";
    private static final String USERNAME_COL = "username";
    private static final String IS_LOGGED_IN = "isloggedin";

    public CurrentAcountDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME_COL + " TEXT)";

//                + USERNAME_COL + " TEXT,"
//                + IS_LOGGED_IN + " INTEGER)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void updateAccount(String username){
        // TODO: edit SQLite
    }

    public String getUsername() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses
                = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        String username = null;
        if (cursorCourses.moveToFirst()) {
            do {
                username = cursorCourses.getString(1);

            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        cursorCourses.close();
        return username;
    }
}
