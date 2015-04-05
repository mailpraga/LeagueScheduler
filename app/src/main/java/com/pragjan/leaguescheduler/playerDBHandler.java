package com.pragjan.leaguescheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class playerDBHandler extends SQLiteOpenHelper {

    public static final String TABLE_PLAYER = "player";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MATCHPLAYED = "matchPlayed";
    public static final String COLUMN_WIN = "win";
    public static final String COLUMN_LOSS = "loss";
    public static final String COLUMN_DRAW = "draw";
    public static final String COLUMN_GOALDIFF = "goalDiff";
    public static final String COLUMN_POINT = "point";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "playerDB.db";
    private static playerDBHandler sInstance;
    private SQLiteDatabase db = null;

    //We need to pass database information along to superclass
    private playerDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public static synchronized playerDBHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new playerDBHandler(context.getApplicationContext());
        }
        return sInstance;
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
                COLUMN_GOALDIFF + " INTEGER, " +
                COLUMN_POINT + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    @Override
    public synchronized void close() {
        if (sInstance != null)
            db.close();
    }

    public Cursor getAllDataOrderByPointAndGoalDiff() {
        String buildSQL = "SELECT * FROM " +
                "(SELECT * FROM " + TABLE_PLAYER + " ORDER BY " + COLUMN_POINT + " DESC)"
                + " ORDER BY " + COLUMN_GOALDIFF + " DESC ";
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
        values.put(COLUMN_GOALDIFF, thePlayer.get_goalDiff());
        values.put(COLUMN_POINT, thePlayer.get_point());

        db.insert(TABLE_PLAYER, null, values);
    }

    public void addPlayerHashMap(HashMap<String, player> thePlayerHashMap) {
        for (Map.Entry<String, player> entry : thePlayerHashMap.entrySet()) {
            addPlayer(entry.getValue());
        }
    }

    public List<player> getPlayersInList() {
        String name;
        int win, matchPlayed, loss, draw, goalDiff, point;

        //Cursor points to a location in your results
        Cursor c = getAllDataOrderByPointAndGoalDiff();
        //Move to the first row in your results
        c.moveToFirst();
        List<player> playerNameList = new ArrayList<>();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            name = c.getString(c.getColumnIndex(COLUMN_NAME));
            matchPlayed = c.getInt(c.getColumnIndex(COLUMN_MATCHPLAYED));
            win = c.getInt(c.getColumnIndex(COLUMN_WIN));
            loss = c.getInt(c.getColumnIndex(COLUMN_LOSS));
            draw = c.getInt(c.getColumnIndex(COLUMN_DRAW));
            goalDiff = c.getInt(c.getColumnIndex(COLUMN_GOALDIFF));
            point = c.getInt(c.getColumnIndex(COLUMN_POINT));
            c.moveToNext();
            playerNameList.add(new player(name, matchPlayed, win, loss, draw, goalDiff, point));
        }

        return playerNameList;
    }


    public HashMap<String, player> getPlayersInHashMap() {
        String name;
        int win, matchPlayed, loss, draw, goalDiff, point;

        //Cursor points to a location in your results
        Cursor c = getAllDataOrderByPointAndGoalDiff();
        //Move to the first row in your results
        c.moveToFirst();
        HashMap<String, player> playerNameHashMap = new HashMap<>();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            name = c.getString(c.getColumnIndex(COLUMN_NAME));
            matchPlayed = c.getInt(c.getColumnIndex(COLUMN_MATCHPLAYED));
            win = c.getInt(c.getColumnIndex(COLUMN_WIN));
            loss = c.getInt(c.getColumnIndex(COLUMN_LOSS));
            draw = c.getInt(c.getColumnIndex(COLUMN_DRAW));
            goalDiff = c.getInt(c.getColumnIndex(COLUMN_GOALDIFF));
            point = c.getInt(c.getColumnIndex(COLUMN_POINT));
            c.moveToNext();
            playerNameHashMap.put(name, new player(name, matchPlayed, win, loss, draw, goalDiff, point));
        }

        return playerNameHashMap;
    }

    void deleteAll() {
        db.delete(TABLE_PLAYER, null, null);
    }
}