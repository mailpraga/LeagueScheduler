package com.pragjan.leaguescheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class leagueMatchTable extends Activity {

    private matchDBHandler mdbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_match_table);

        mdbHandler = matchDBHandler.getInstance(this);

        Bundle enterPlayerActivity = getIntent().getExtras();
        if(enterPlayerActivity == null){
            return;
        }
        int TotalNumberOfMatch = enterPlayerActivity.getInt("TotalNumberOfMatch");
        ArrayList<match> arrayOfmatch = new ArrayList<match>();
        for( int i = 1;i <= TotalNumberOfMatch; i++){
            match theMatch = mdbHandler.getMatch(i);
            arrayOfmatch.add(theMatch);
        }

        ListAdapter matchTableAdapter = new custom_matchTableAdapter(this,arrayOfmatch);
        ListView leagueMatchTableListView = (ListView) findViewById(R.id.leagueMatchTableListView);
        leagueMatchTableListView.setAdapter(matchTableAdapter);
    }

    public void goToPointTable(View v) {
        Intent i = new Intent(this, pointTable.class);
        startActivity(i);
    }

}
