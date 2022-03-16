package com.example.bookshare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // create variables
    // create database name BookShare
    final static String DATABASE_NAME="BookShare.db";
    final static int DATABASE_VERSION=3;
    // create Table 1 User Table Properties -- Ashish Gujral
    final static String TABLE1_NAME="User_table";
    final static String T1COL1="Email";
    final static String T1COL2="UserName";
    final static String T1COL3="Password";
    final static String T1COL4="Age";
    final static String T1COL5="Address";
    final static String T1COL6="ReadingInterest";
    // create Table 2 Admin Table Properties -- Ashish Gujral
    final static String TABLE2_NAME="Admin_table";
    final static String T2COL1="Email";
    final static String T2COL2="Password";
    // create Table 3 Book Table Properties -- Eunchong Choi
    final static String TABLE3_Name = "Book_table";
    final static String T3COL1 = "Id";
    final static String T3COL2 = "Title";
    final static String T3COL3 = "Author";
    final static String T3COL4 = "Publisher";
    final static String T3COL5 = "PublicationYear";
    final static String T3COL6 = "Status";


    // Constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //query to create a User table -- Ashish Gujral
        String query = "CREATE TABLE "+ TABLE1_NAME+"("+T1COL1+" TEXT PRIMARY KEY,"+
                T1COL2+" TEXT,"+T1COL3+" TEXT,"+T1COL4+" TEXT,"+ T1COL5 +" TEXT,"+ T1COL6+" TEXT)";
        db.execSQL(query);
        //query for admin table -- Ashish Gujral
        query = "CREATE TABLE "+ TABLE2_NAME+"("+T2COL1+" TEXT PRIMARY KEY,"+
                T2COL2+" TEXT)";

        // query for Book Table--Eunchong Choi
        query = "CREATE TABLE " +TABLE3_Name + "(" + T3COL1 + " INTEGER PRIMARY KEY," +
                T3COL2 + " TEXT," + T3COL3 + " TEXT," + T3COL4 + " TEXT," + T3COL5 + " TEXT,"+ T3COL6 + " TEXT)";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE3_Name);
        onCreate(db);
    }

    // Add user function  --Ashish gujral
    public boolean addUser(String email,String name,String password,
                           String age,String address,String Reading){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(T1COL1,email);
        value.put(T1COL2,name);
        value.put(T1COL3,password);
        value.put(T1COL4,age);
        value.put(T1COL5,address);
        value.put(T1COL6,Reading);

        long r= sqLiteDatabase.insert(TABLE1_NAME,null,value);
        if(r >0)
            return  true;
        else
            return  false;
    }
    // for inserting admin data -- Ashish Gujral
    public boolean addAdmin(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(T2COL1,"admin");
        value.put(T2COL2,"admin");
        long r= sqLiteDatabase.insert(TABLE2_NAME,null,value);
        if(r >0)
            return  true;
        else
            return  false;
    }

//    // View Function to fetch the email id and password--Ashish Gujral
//    public Cursor viewUserRecord(String email){
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String query = "SELECT email,password FROM "+ TABLE1_NAME +
//                " where email='\'"+email+"'\'";
//        Cursor c=sqLiteDatabase.rawQuery(query,null);
//        return c;
//
//    }
    // method for checking user email already exist or not--Ashish Gujral
    public Boolean checkEmail(String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("Select * from " +TABLE1_NAME+
                " where email =? ",new String[]{email});
        if(c.getCount()>0)
            return true;
        else
            return false;
    }
    // method for checking user id and password--Ashish Gujral
    public Boolean checkEmailPassword(String Email,String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("Select * from " +TABLE1_NAME+
                " where email =? and password =? ",new String[]{Email,password});
        if(c.getCount()>0)
            return true;
        else
            return false;
    }
    // methods for check admin login id or password--Ashish Gujral
    public Boolean checkAdmin(String Email,String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("Select * from " +TABLE2_NAME+
                " where email =? and password =? ",new String[]{Email,password});
        if(c.getCount()>0)
            return true;
        else
            return false;
    }

    // this method is used to check all the fields are correct for forget password--Ashish Gujral
    public Boolean checkForget(String age,String address,String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("Select * from " +TABLE1_NAME+
                " where age =? and address =? and email=? ",new String[]{age,address,email});
        if(c.getCount()>0)
            return true;
        else
            return false;
    }
    // this table is used to update the new password -- Ashish Gujral
    public boolean UpdatePassword(String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL3,password);
        int u=sqLiteDatabase.update(TABLE1_NAME,values,"email=?",
                new String[]{email});
        if(u>0)
            return true;
        else
            return false;
    }
    // Add book record table -- Eunchong Choi
    public boolean addRecord(String Title, String Author, String Publisher,
                             String PublicationYear,String status){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(T3COL2,Title);
        value.put(T3COL3,Author);
        value.put(T3COL4,Publisher);
        value.put(T3COL5,PublicationYear);
        value.put(T3COL6,status);
        long r = sqLiteDatabase.insert(TABLE3_Name,null,value);
        if(r>0)
            return true;
        else
            return false;
    }
    // Update book record table -- Eunchong Choi
    public boolean updateRec(int id, String title, String author, String publisher, String year) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL1, id);
        values.put(T3COL2, title);
        values.put(T3COL3, author);
        values.put(T3COL4, publisher);
        values.put(T3COL5, year);

        int u = sqLiteDatabase.update(TABLE3_Name,values,"id=?", new String[]{Integer.toString(id)});

        if(u>0)
            return true;
        else
            return false;
    }
    // View book record table -- Eunchong Choi
    public Cursor viewRecord(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE3_Name;
        Cursor c =sqLiteDatabase.rawQuery(query,null);
        return c;
    }
    // View book record table for particular Field -- Eunchong Choi
    public Cursor viewBookRecord(String status){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("Select title,Id from " +TABLE3_Name+
                " where satus =?",new String[]{status});
      /*  String query = "SELECT email,password FROM "+ TABLE1_NAME +
//                " where email='\'"+email+"'\'";
//        Cursor c=sqLiteDatabase.rawQuery(query,null);
//        return c;*/
        return c;
    }

}
