package com.pragjan.leaguescheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class matchDBHandler extends SQLiteOpenHelper {

    public static final String TABLE_MATCH = "MATCH";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MATCH_NO = "matchNo";
    public static final String COLUMN_HOME1 = "home1";
    public static final String COLUMN_HOME2 = "home2";
    public static final String COLUMN_HOMEGOAL = "homeGoal";
    public static final String COLUMN_GUEST1 = "guest1";
    public static final String COLUMN_GUEST2 = "guest2";
    public static final String COLUMN_GUESTGOAL = "guestGoal";
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "matchDB.db";
    private static matchDBHandler sInstance;
    private SQLiteDatabase db = null;

    //We need to pass database information along to superclass
    private matchDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public static synchronized matchDBHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new matchDBHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_MATCH + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MATCH_NO + " INTEGER, " +
                COLUMN_HOME1 + " TEXT, " +
                COLUMN_HOME2 + " TEXT, " +
                COLUMN_HOMEGOAL + " INTEGER, " +
                COLUMN_GUEST1 + " TEXT, " +
                COLUMN_GUEST2 + " TEXT, " +
                COLUMN_GUESTGOAL + " INTEGER " +
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
        values.put(COLUMN_MATCH_NO, theMatch.get_matchNo());
        values.put(COLUMN_HOME1, theMatch.get_home1());
        values.put(COLUMN_HOME2, theMatch.get_home2());
        values.put(COLUMN_HOMEGOAL, theMatch.get_homeGoal());
        values.put(COLUMN_GUEST1, theMatch.get_guest1());
        values.put(COLUMN_GUEST2, theMatch.get_guest2());
        values.put(COLUMN_GUESTGOAL, theMatch.get_guestGoal());

        db.insert(TABLE_MATCH, null, values);
    }

    public List<match> getMatches() {
        String home1, home2, guest1, guest2;
        int matchNo, homeGoal, guestGoal;


        //Cursor points to a location in your results
        Cursor c = getAllData();
        //Move to the first row in your results
        c.moveToFirst();
        List<match> theMatchList = new ArrayList<>();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            matchNo = c.getInt(c.getColumnIndex(COLUMN_MATCH_NO));
            home1 = c.getString(c.getColumnIndex(COLUMN_HOME1));
            home2 = c.getString(c.getColumnIndex(COLUMN_HOME2));
            homeGoal = c.getInt(c.getColumnIndex(COLUMN_HOMEGOAL));
            guest1 = c.getString(c.getColumnIndex(COLUMN_GUEST1));
            guest2 = c.getString(c.getColumnIndex(COLUMN_GUEST2));
            guestGoal = c.getInt(c.getColumnIndex(COLUMN_GUESTGOAL));
            c.moveToNext();
            theMatchList.add(new match(matchNo, home1, home2, homeGoal, guest1, guest2, guestGoal));
        }

        return theMatchList;
    }

    public List<match> getMatchFromMatchNo(int matchNo) {
        String home1, home2, guest1, guest2;
        int homeGoal, guestGoal;

        //Cursor points to a location in your results
        String buildSQL = "SELECT * FROM " + TABLE_MATCH + " WHERE " + COLUMN_MATCH_NO + " = " + matchNo;
        Cursor c = db.rawQuery(buildSQL, null);

        //Move to the first row in your results
        c.moveToFirst();
        List<match> theMatchList = new ArrayList<>();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            matchNo = c.getInt(c.getColumnIndex(COLUMN_MATCH_NO));
            home1 = c.getString(c.getColumnIndex(COLUMN_HOME1));
            home2 = c.getString(c.getColumnIndex(COLUMN_HOME2));
            homeGoal = c.getInt(c.getColumnIndex(COLUMN_HOMEGOAL));
            guest1 = c.getString(c.getColumnIndex(COLUMN_GUEST1));
            guest2 = c.getString(c.getColumnIndex(COLUMN_GUEST2));
            guestGoal = c.getInt(c.getColumnIndex(COLUMN_GUESTGOAL));
            c.moveToNext();
            theMatchList.add(new match(matchNo, home1, home2, homeGoal, guest1, guest2, guestGoal));
        }

        return theMatchList;
    }

    public void setScoreForMatchNo(int matchNo, int homeGoal, int guestGoal) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_HOMEGOAL, homeGoal);
        cv.put(COLUMN_GUESTGOAL, guestGoal);
        int update = db.update(TABLE_MATCH, cv, COLUMN_MATCH_NO + "=" + matchNo, null);
        assert (update != 1);
    }
    public void deleteAll() {
        db.delete(TABLE_MATCH, null, null);
    }
}



