package com.pragjan.leaguescheduler;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class playerDBHandler extends SQLiteOpenHelper {

    private static playerDBHandler sInstance;
    private SQLiteDatabase db = null;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "playerDB.db";
    public static final String TABLE_PLAYER = "player";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MATCHPLAYED = "matchPlayed";
    public static final String COLUMN_WIN = "win";
    public static final String COLUMN_LOSS = "loss";
    public static final String COLUMN_DRAW = "draw";
    public static final String COLUMN_POINT = "point";

    public static synchronized playerDBHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new playerDBHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    //We need to pass database information along to superclass
    private playerDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PLAYER + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_WIN + " INTEGER, " +
                COLUMN_MATCHPLAYED + " INTEGER, " +
                COLUMN_LOSS + " INTEGER, " +
                COLUMN_DRAW + " INTEGER, " +
                COLUMN_POINT + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    @Override
    public synchronized void close() {
        if (sInstance != null)
            db.close();
    }

    public Cursor getAllDataOrderByPoint() {
        String buildSQL = "SELECT * FROM " + TABLE_PLAYER + " ORDER BY " + COLUMN_POINT + " DESC";
        return db.rawQuery(buildSQL, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        onCreate(db);
    }

    //Add a new row to the database
    public void addPlayer(player thePlayer) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, thePlayer.get_name());
        values.put(COLUMN_WIN, thePlayer.get_win());
        values.put(COLUMN_MATCHPLAYED, thePlayer.get_matchPlayed());
        values.put(COLUMN_LOSS, thePlayer.get_loss());
        values.put(COLUMN_DRAW, thePlayer.get_draw());
        values.put(COLUMN_POINT, thePlayer.get_point());

        db.insert(TABLE_PLAYER, null, values);
    }

    public void addPlayerList(List<player> thePlayerList){
        for(int i = 0; i < thePlayerList.size();i++){
            addPlayer(thePlayerList.get(i));
        }
    }


    public List<player> getPlayers() {
        String name = "";
        int win = 0;
        int matchPlayed = 0;
        int loss = 0;
        int draw = 0;
        int point = 0;

        //Cursor points to a location in your results
        Cursor c = getAllDataOrderByPoint();
        //Move to the first row in your results
        c.moveToFirst();
        List<player> thePlayerList = new ArrayList<player>();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            name = c.getString(c.getColumnIndex(COLUMN_NAME));
            matchPlayed = c.getInt(c.getColumnIndex(COLUMN_MATCHPLAYED));
            win = c.getInt(c.getColumnIndex(COLUMN_WIN));
            loss = c.getInt(c.getColumnIndex(COLUMN_LOSS));
            draw = c.getInt(c.getColumnIndex(COLUMN_DRAW));
            point = c.getInt(c.getColumnIndex(COLUMN_POINT));
            c.moveToNext();
            thePlayerList.add(new player(name, matchPlayed, win, loss, draw, point));
        }

        return thePlayerList;
    }

    void deleteAll() {
        db.delete(TABLE_PLAYER, null, null);
    }

    public void updatePointTable(String thePlayer, String W, String MP, String L, String D, String P) {
        String buildSQL = "UPDATE " + TABLE_PLAYER + " SET  ='" + W + " WHERE " + COLUMN_NAME + " = " + thePlayer;
        db.rawQuery(buildSQL, null);
    }

}