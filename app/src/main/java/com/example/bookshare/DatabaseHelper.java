package com.example.bookshare;

import android.content.ContentValues;
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

    final static String TABLE2_NAME = "History_table";
    final static String T2COL1 = "Id";
    final static String T2COL2 = "BookName";
    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    // ashish
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addHistory(String bookName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(T2COL2, bookName);
        long result = sqLiteDatabase.insert(TABLE2_NAME, null, value);

        if(result >0)
            return true;
        else
            return false;
    }
}
