package com.lalagrass.android_logger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoggerStaticUtils {

    public static synchronized long PutData(Context context, String msg) {
        LoggerDbHelper mDbHelper = new LoggerDbHelper(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long date = new Date().getTime();
// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(LoggerDbHelper.LoggerEntry.COLUMN_NAME_DATE, date);
        values.put(LoggerDbHelper.LoggerEntry.COLUMN_NAME_MSG, msg);

// Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                LoggerDbHelper.LoggerEntry.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    public static List<LoggerData> GetData(Context context) {
        List<LoggerData> tags = new ArrayList<>();
        LoggerDbHelper mDbHelper = new LoggerDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                LoggerDbHelper.LoggerEntry._ID,
                LoggerDbHelper.LoggerEntry.COLUMN_NAME_DATE,
                LoggerDbHelper.LoggerEntry.COLUMN_NAME_MSG,
        };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                LoggerDbHelper.LoggerEntry.COLUMN_NAME_DATE + " DESC";

        Cursor c = db.query(
                LoggerDbHelper.LoggerEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        if (c.moveToFirst()) {
            do {
                LoggerData t = new LoggerData();
                t.LoggerMsg = c.getString(c.getColumnIndex(LoggerDbHelper.LoggerEntry.COLUMN_NAME_MSG));
                t.LoggerDate = c.getLong(c.getColumnIndex(LoggerDbHelper.LoggerEntry.COLUMN_NAME_DATE));
                tags.add(t);
            } while (c.moveToNext());
        }
        return tags;
    }
}
