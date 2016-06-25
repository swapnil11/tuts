package com.example.myapplication.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by srg11 on 6/25/2016.
 */
public class SampleDatabaseHelper {

    private static final String TAG = SampleDatabaseHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase.db";

    // table configuration
    private static final String TABLE_NAME = "emp_table";
    private static final String _ID = "_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;

    public SampleDatabaseHelper(Context aContext) {

        openHelper = new DatabaseOpenHelper(aContext);
        database = openHelper.getWritableDatabase();
    }

    public void insertData(String firstName, String lastName) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(FIRST_NAME, firstName);
        contentValues.put(LAST_NAME, lastName);

        database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllData() {

        String buildSQL = "SELECT * FROM " + TABLE_NAME;

        Log.d(TAG, "getAllData SQL: " + buildSQL);

        return database.rawQuery(buildSQL, null);
    }

    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        public DatabaseOpenHelper(Context aContext) {
            super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String buildSQL = "CREATE TABLE " + TABLE_NAME + "( " + _ID + " INTEGER PRIMARY KEY, " +
                    FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT )";

            Log.d(TAG, "onCreate SQL: " + buildSQL);

            sqLiteDatabase.execSQL(buildSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            String buildSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;

            Log.d(TAG, "onUpgrade SQL: " + buildSQL);

            sqLiteDatabase.execSQL(buildSQL);

            onCreate(sqLiteDatabase);
        }
    }
}
