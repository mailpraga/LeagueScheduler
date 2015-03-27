package com.pragjan.leaguescheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class pointTable extends Activity {
    private playerDBHandler pdbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_table);

        pdbHandler = playerDBHandler.getInstance(this);

        List<player> arrayOfPlayers = new ArrayList<player>();
        arrayOfPlayers = pdbHandler.getPlayers();

        ListAdapter pointTableAdapter = new custom_pointTableAdapter(this,arrayOfPlayers);
        ListView pointTableListView = (ListView) findViewById(R.id.pointTableListView);
        pointTableListView.setAdapter(pointTableAdapter);
    }

    public void goToleagueMatchTable(View v) {
        Intent i = new Intent(this, leagueMatchTable.class);
        startActivity(i);
    }



}
