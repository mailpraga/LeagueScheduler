package com.pragjan.leaguescheduler;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.text.TextUtils;

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
    public static final String COLUMN_MATCHPARTNER = "matchPartner";
    public static final String COLUMN_MATCHENEMY = "matchEnemy";
    public static final String COLUMN_MATCHPARTNERIND = "matchPartnerInd";
    public static final String COLUMN_MATCHENEMYND = "matchEnemyInd";
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
                COLUMN_MATCHPARTNER + " TEXT, " +
                COLUMN_MATCHENEMY + " TEXT, " +
                COLUMN_MATCHPARTNERIND + " INT, " +
                COLUMN_MATCHENEMYND + " INT, " +
                COLUMN_WIN + " INT, " +
                COLUMN_MATCHPLAYED + " INT, " +
                COLUMN_LOSS + " INT, " +
                COLUMN_DRAW + " INT, " +
                COLUMN_POINT + " INT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public synchronized void close() {
        if (sInstance != null)
            db.close();
    }

    public Cursor getAllData() {
        String buildSQL = "SELECT * FROM " + TABLE_PLAYER;
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
        values.put(COLUMN_MATCHPARTNER, TextUtils.join(", ", thePlayer.get_matchPartner()));
        values.put(COLUMN_MATCHENEMY, TextUtils.join(", ", thePlayer.get_matchEnemy()));
        values.put(COLUMN_MATCHPARTNERIND, thePlayer.get_matchPartnerInd());
        values.put(COLUMN_MATCHENEMYND, thePlayer.get_matchEnemyInd());
        values.put(COLUMN_WIN, thePlayer.get_win());
        values.put(COLUMN_MATCHPLAYED, thePlayer.get_matchPlayed());
        values.put(COLUMN_LOSS, thePlayer.get_loss());
        values.put(COLUMN_DRAW, thePlayer.get_draw());
        values.put(COLUMN_POINT, thePlayer.get_point());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PLAYER, null, values);
        db.close();
    }

    void deleteAll()
    {
        SQLiteDatabase db= getWritableDatabase();
        db.delete(TABLE_PLAYER, null, null);
        db.close();
    }
    //Delete a product from the database
    public void deletePlayer(String thePlayerName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAYER + " WHERE " + COLUMN_NAME + "=\"" + thePlayerName + "\";");
        db.close();
    }

    public String getColumnPoint(String thePlayerName) {
        String dbString = "";
        Cursor c = getCursor(thePlayerName);
        c.moveToFirst();
        if (c.getString(c.getColumnIndex("COLUMN_POINT")) != null) {
            dbString += c.getString(c.getColumnIndex("COLUMN_POINT"));
        }
        return dbString;
    }

    public Cursor getCursor(String thePlayerName){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PLAYER + " WHERE 1" + COLUMN_NAME + "=\"" + thePlayerName + "\"";
        Cursor c = db.rawQuery(query, null);
        db.close();
        return c;
    }

    public String[] getMatchPartner(int id){
        String[] sArray = {""};
        SQLiteDatabase db = getReadableDatabase();
        String from[] = { COLUMN_MATCHPARTNER};
        Cursor c = db.query(TABLE_PLAYER, from, "_id = " + id,
                null, null, null, null);
        if(c!=null)
        {
            c.moveToFirst();
            String dbString = c.getString(c.getColumnIndex(COLUMN_MATCHPARTNER));
            sArray = TextUtils.split(dbString,", ");
        }
        return sArray;
    }

    public void appendMatchPartner(int id1, int id2){
        List<String> sArray = Arrays.asList(getMatchPartner(id1));
        String s = getName(id2);
        sArray.add(s);
        s = TextUtils.join(", ", sArray);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MATCHPARTNER, s);
        db.update(TABLE_PLAYER, cv,"_id = " + id1, null);
        db.close();
    }

    public String getName(int id){
        String dbString = "";
        SQLiteDatabase db = getReadableDatabase();
        String from[] = {COLUMN_NAME};
        Cursor c = db.query(TABLE_PLAYER, from, "_id = " + id,
                null, null, null, null);
        if(c!=null)
        {
            c.moveToFirst();
            int cind = c.getColumnIndex(COLUMN_NAME);
            dbString = c.getString(cind);
        }
        return dbString;
    }

    public int getMatchPartnerInd(int id){
        int dbInt = 0;
        SQLiteDatabase db = getReadableDatabase();
        String from[] = {COLUMN_MATCHPARTNERIND};
        Cursor c = db.query(TABLE_PLAYER, from, "_id = " + id,
                null, null, null, null);
        if(c!=null)
        {
            c.moveToFirst();
            dbInt = c.getInt(c.getColumnIndex(COLUMN_MATCHPARTNERIND));
        }
        return dbInt;
    }

    public boolean findMatchPartner(int id1, int id2) {
        if (getName(id1).equals(getName(id2))) {return true;}
        if (getMatchPartnerInd(id1)==1) {return false;}
        String[] partner = getMatchPartner(id1);
        if (getMatchPartnerInd(id1) < partner.length/2){
            return Arrays.asList(partner).contains(getName(id2));
        }else{
            if (Collections.frequency(Arrays.asList(partner), getName(id2))== 1){
                return false;
            }else {
                return true;
            }
        }
    }

}