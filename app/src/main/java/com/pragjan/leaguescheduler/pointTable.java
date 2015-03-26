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


public class pointTable extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_table);

        //Bundle MainActivity = getIntent().getExtras();
        //if(MainActivity == null){
        //    return;
        //}

        ArrayList<player> arrayOfPlayer = new ArrayList<player>();
        arrayOfPlayer.add(new player("Praga",4));

        ListAdapter pointTableAdapter = new custom_pointTableAdapter(this,arrayOfPlayer);
        ListView pointTableListView = (ListView) findViewById(R.id.pointTableListView);
        pointTableListView.setAdapter(pointTableAdapter);
    }

    public void goToleagueMatchTable(View v) {
        Intent i = new Intent(this, leagueMatchTable.class);

        startActivity(i);
    }



}
