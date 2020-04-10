package com.example.videorecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MinalDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pathtable";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_PATH = "PATH";

    DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE "
                + TABLE_NAME
                + " ("
                + COLUMN_ID
                + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PATH
                + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public boolean insertToTable(String path) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PATH, path);
        return db.insert(TABLE_NAME, null, contentValues) != -1;
    }

    public ArrayList<RecordItem> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from pathtable ", null);
        try {

            ArrayList<RecordItem> recordings = new ArrayList<>();

            //hp = new HashMap();
            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        RecordItem gl = new RecordItem(cursor.getString(0), cursor.getString(1));
                        recordings.add(gl);
                    } while (cursor.moveToNext());
                }
            }
            return recordings;
        } finally {
            cursor.close();
        }
    }
}

