package com.pragjan.leaguescheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class leagueMatchTable extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_match_table);

        Bundle MainActivity = getIntent().getExtras();
        if(MainActivity == null){
            return;
        }
        ArrayList<match> arrayOfmatch = new ArrayList<match>();
        arrayOfmatch.add(new match("praga","adi","chirag","chetan"));


        ListAdapter matchTableAdapter = new custom_matchTableAdapter(this,arrayOfmatch);
        ListView leagueMatchTableListView = (ListView) findViewById(R.id.leagueMatchTableListView);
        leagueMatchTableListView.setAdapter(matchTableAdapter);

    }

    public void goToPointTable(View v) {
        Intent i = new Intent(this, pointTable.class);
        startActivity(i);
    }

}
