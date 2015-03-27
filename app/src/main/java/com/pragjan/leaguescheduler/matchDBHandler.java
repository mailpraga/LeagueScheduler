package com.pragjan.leaguescheduler;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;


public class matchDBHandler extends SQLiteOpenHelper {

    private static matchDBHandler sInstance;
    private SQLiteDatabase db = null;

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "matchDB.db";
    public static final String TABLE_MATCH = "MATCH";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_HOME1 = "home1";
    public static final String COLUMN_HOME2 = "home2";
    public static final String COLUMN_GUEST1 = "guest1";
    public static final String COLUMN_GUEST2 = "guest2";

    public static synchronized matchDBHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new matchDBHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    //We need to pass database information along to superclass
    private matchDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_MATCH + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_HOME1 + " TEXT, " +
                COLUMN_HOME2 + " TEXT, " +
                COLUMN_GUEST1 + " TEXT, " +
                COLUMN_GUEST2 + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public synchronized void close() {
        if (sInstance != null)
            db.close();
    }

    public Cursor getAllData() {
        String buildSQL = "SELECT * FROM " + TABLE_MATCH;
        return db.rawQuery(buildSQL, null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCH);
        onCreate(db);
    }

    //Add a new row to the database
    public void addMatch(match theMatch) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_HOME1, theMatch.get_home1());
        values.put(COLUMN_HOME2, theMatch.get_home2());
        values.put(COLUMN_GUEST1, theMatch.get_guest1());
        values.put(COLUMN_GUEST2, theMatch.get_guest2());

        db.insert(TABLE_MATCH, null, values);
    }

    public List<match> getMatches() {
        String home1 = "";
        String home2 = "";
        String guest1 = "";
        String guest2 = "";

        //Cursor points to a location in your results
        Cursor c = getAllData();
        //Move to the first row in your results
        c.moveToFirst();
        List<match> theMatchList = new ArrayList<match>();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            home1 = c.getString(c.getColumnIndex(COLUMN_HOME1));
            home2 = c.getString(c.getColumnIndex(COLUMN_HOME2));
            guest1 = c.getString(c.getColumnIndex(COLUMN_GUEST1));
            guest2 = c.getString(c.getColumnIndex(COLUMN_GUEST2));
            c.moveToNext();
            theMatchList.add(new match(home1, home2, guest1, guest2));
        }

        return theMatchList;
    }

    public void deleteAll() {
        db.delete(TABLE_MATCH, null, null);
    }
}



