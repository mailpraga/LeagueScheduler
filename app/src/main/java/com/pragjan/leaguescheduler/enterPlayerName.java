package com.pragjan.leaguescheduler;

import android.app.Activity ;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class enterPlayerName extends Activity  {
    private Spinner numPlayerSpinner;
    playerDBHandler pdbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_player_name);
        numPlayerSpinner = (Spinner) findViewById(R.id.numPlayerSpinner);
        addEntriesToNumPlayerSpinner();
        numPlayerSpinner.setOnItemSelectedListener(new CustomnumPlayerSpinnerOnItemSelectedListener(this));

        pdbHandler = new playerDBHandler(this, null, null, 1);
    }

    public void addEntriesToNumPlayerSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Number_array, android.R.layout.simple_dropdown_item_1line);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        // Apply the adapter to the spinner
        numPlayerSpinner.setAdapter(adapter);
    }

    public void onClickGenerate(View v) {
        Intent i = new Intent(this, leagueMatchTable.class);
        int numPlayer = Integer.parseInt(numPlayerSpinner.getSelectedItem().toString());
        i.putExtra("numPlayer", numPlayer);

        final ListView playerNameListView = (ListView) findViewById(R.id.enterPlayerNameListView);
        String playerName;

        for(int item = 0; item < playerNameListView.getAdapter().getCount(); item++){
            View vplayerNameListView = playerNameListView.getChildAt(item);
            playerName = ((EditText)vplayerNameListView.findViewById(R.id.enterPlayerNameEditText)).getText().toString();
            player thePlayer = new player(playerName,numPlayer);
            pdbHandler.addPlayer(thePlayer);
        }

        startActivity(i);
    }
}
