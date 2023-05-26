package com.example.docapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "DocApp.db";
    private static final int DATABASE_VERSION = 1;

    // table docs
    private static final String TABLE_NAME = "docs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SPEC = "spec";
    private static final String COLUMN_LOCATION = "location";

    // table users
    private static final String TABLE_NAME2 = "users";
    private static final String COLUMN_ID2 = "_id";
    private static final String COLUMN_USER = "username";
    private static final String COLUMN_PASS = "password";

    //table appointments
    private static final String TABLE_NAME3 = "appointments";
    private static final String COLUMN_ID3 = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_DOCNAME = "docname";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_TYPE = "type";



    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SPEC + " TEXT, " +
                COLUMN_LOCATION + " TEXT);";

        String query2 = "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PASS + " TEXT, " +
                COLUMN_USER + " TEXT);";
        String query3 = "CREATE TABLE " + TABLE_NAME3 +
                " (" + COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_DOCNAME + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_TYPE + " TEXT);";

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
    }

    void addDoc(String name, String spec, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SPEC, spec);
        cv.put(COLUMN_LOCATION, location);
        long result = db.insert(TABLE_NAME, null, cv);

        Toast.makeText(context, "Succes!", Toast.LENGTH_SHORT).show();
    }

    void addUser(String username, String password){


        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = MyDB.insert("users", null, contentValues);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Account " + username + " created!", Toast.LENGTH_SHORT).show();
        }

    }

    void makeAppointment(String username, String docname, String date, String time, String type){


        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_DOCNAME, docname);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_TYPE, type);

        long result = MyDB.insert("appointments", null, contentValues);

        if(result != -1){
            Toast.makeText(context, "Appointment created!", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }

    Cursor readAllDataAppointments(String USERNAME){
        String query = "SELECT * FROM " + TABLE_NAME3 + " where username = '" + USERNAME + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkDate(String docname, String date, String time){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from appointments where docname = ? and date = ? and time = ?", new String[] {docname, date, time});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkHour(String docname, String date, String time){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from appointments where docname = ? and date = ? and  time = ?", new String[] {docname, date, time});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
}
