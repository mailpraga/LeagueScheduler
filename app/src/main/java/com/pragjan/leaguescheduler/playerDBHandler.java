package com.pragjan.leaguescheduler;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class playerDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
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

    //We need to pass database information along to superclass
    public playerDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PLAYER + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT " +
                COLUMN_MATCHPARTNER + " TEXT " +
                COLUMN_MATCHENEMY + " TEXT " +
                COLUMN_MATCHPARTNERIND + " INT " +
                COLUMN_MATCHENEMYND + " INT " +
                COLUMN_WIN + " INT " +
                COLUMN_MATCHPLAYED + " INT " +
                COLUMN_LOSS + " INT " +
                COLUMN_DRAW + " INT " +
                COLUMN_POINT + " INT " +
                ");";
        db.execSQL(query);
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
        values.put(COLUMN_MATCHPARTNER, thePlayer.get_matchPartner());
        values.put(COLUMN_MATCHENEMY, thePlayer.get_matchEnemy());
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

    //Delete a product from the database
    public void deleteProduct(String thePlayer) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAYER + " WHERE " + COLUMN_NAME + "=\"" + thePlayer + "\";");
    }

    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PLAYER + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("name")) != null) {
                dbString += c.getString(c.getColumnIndex("name"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

}