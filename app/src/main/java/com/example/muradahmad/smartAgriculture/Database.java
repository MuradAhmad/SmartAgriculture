
package com.example.muradahmad.smartAgriculture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {


    public static final String USER_DATABASE = "database.db";

    // RuuviTag Device Table

    public static final String DEVICE_TABLE = "Device_Table";
    public static final String DEVICE_ID = "Id";
    public static final String URL = "Url";
    public static final String RSSI = "Rssi";
    public static final String TEMPERATURE = "Temperature";
    public static final String HUMIDITY = "Humidity";
    //public static final String PRESSURE = "Pressure";
    public static final String DATE = "Timestamp";

    // Plants Table

    public static final String PLANTS_TABLE = "Plants_Table";
    public static final String PLANT_NAME = "Plant_name";
    public static final String TIMESTAMP = "Timestamp";

    public static Database instance;

    public static  Database getInstance(Context context) {
        if (instance == null) {
            instance = new Database(context);
        }
        return instance;
    }


    public Database(Context context) {
        super(context, USER_DATABASE, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DEVICE_TABLE + "(Id TEXT, Url TEXT, Rssi TEXT, Temperature TEXT, Humidity TEXT, Presure TEXT, Timestamp REAL )");
        db.execSQL("create table " + PLANTS_TABLE + "(Plant_name TEXT, Timestamp REAL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + DEVICE_TABLE);
        db.execSQL(" DROP TABLE IF EXISTS " + PLANTS_TABLE);

        onCreate(db);

    }

    public boolean insertDeviceData(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.insert(DEVICE_TABLE, null, contentValues);
        return result != -1;

    }

    public boolean insertPlantData(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.insert(PLANTS_TABLE, null, contentValues);
        return result != -1;

    }
    public Cursor viewPlantsData(){
        String selectQuery= "SELECT * FROM " + PLANTS_TABLE ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;

    }

    public Cursor viewDeviceData(){
        String selectQuery= "SELECT * FROM " + DEVICE_TABLE +" ORDER BY DATE DESC LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;

    }

    public Boolean deletePlant(String plant){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Database.PLANTS_TABLE,Database.PLANT_NAME +"=?", new String[] {plant})>0;
    }




}

