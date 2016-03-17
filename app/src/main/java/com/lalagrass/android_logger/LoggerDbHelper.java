package com.lalagrass.android_logger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class LoggerDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "logger.db";

    // default properties
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    // utils
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LoggerEntry.TABLE_NAME + " (" +
                    LoggerEntry._ID + " INTEGER PRIMARY KEY," +
                    LoggerEntry.COLUMN_NAME_DATE + INTEGER_TYPE + COMMA_SEP +
                    LoggerEntry.COLUMN_NAME_MSG + TEXT_TYPE +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LoggerEntry.TABLE_NAME;

    // contract
    public static abstract class LoggerEntry implements BaseColumns {
        public static final String TABLE_NAME = "loggerTable";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_MSG = "msg";
    }

    public LoggerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
