package com.saral.hospitalmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "Patients.db";
    public static final String TableName = "Patients";
    public  static final  String COL1 = "SN";
    public static final String  COL2 = "NAME";
    public static final String  COL3 = "AGE";
    public static final String  COL4 = "GENDER";
    public static final String  COL5 = "CONTACT";
    public static final String  COL6 = "ADDRESS";


    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TableName + " (SN INTEGER PRIMARY KEY AUTOINCREMENT, NAME STRING, AGE INTEGER, GENDER STRING, CONTACT INTEGER, ADDRESS STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TableName);
        onCreate(db);
    }


    public boolean insertData(String Name, String Age, String Gender, String Contact, String Address){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, Name);
        contentValues.put(COL3, Age);
        contentValues.put(COL4, Gender);
        contentValues.put(COL5, Contact);
        contentValues.put(COL6, Address);
        long res = this.getWritableDatabase().insert(TableName, null, contentValues);
        return res != 1;
    }
}
