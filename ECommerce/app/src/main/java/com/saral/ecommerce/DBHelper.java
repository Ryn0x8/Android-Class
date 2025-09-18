package com.saral.ecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ECommerce.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Products";
    private static final String COL_ID = "ProductID";
    private static final String COL_NAME = "ProductName";
    private static final String COL_RETAILER = "RetailerName";
    private static final String COL_PRICE = "Price";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY, " +
                COL_NAME + " TEXT, " +
                COL_RETAILER + " TEXT, " +
                COL_PRICE + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertProduct(int id, String name, String retailer, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_NAME, name);
        values.put(COL_RETAILER, retailer);
        values.put(COL_PRICE, price);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public int deleteByPrice(double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_PRICE + "=?", new String[]{String.valueOf(price)});
    }

    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}

