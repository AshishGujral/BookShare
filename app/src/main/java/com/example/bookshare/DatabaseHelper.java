package com.example.bookshare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // create variables
    final static String DATABASE_NAME="BookShare.db";
    final static int DATABASE_VERSION=1;
    final static String TABLE1_NAME="Student_table";
    final static String T1COL1="Id";
    final static String T1COL2="FName";
    final static String T1COL3="LName";
    final static String T1COL4="Cell_Number";
    final static String T1COL5="ProvId";
    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
