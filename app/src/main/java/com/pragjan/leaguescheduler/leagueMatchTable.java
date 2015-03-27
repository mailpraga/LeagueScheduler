package com.pragjan.leaguescheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class leagueMatchTable extends Activity {

    private playerDBHandler pdbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_match_table);

        pdbHandler = playerDBHandler.getInstance(this);
        matchDBHandler mdbHandler = matchDBHandler.getInstance(this);

        Bundle enterPlayerActivity = getIntent().getExtras();
        if (enterPlayerActivity == null) {
            return;
        }

        List<match> arrayOfMatch;
        arrayOfMatch = mdbHandler.getMatches();

        ListAdapter matchTableAdapter = new custom_matchTableAdapter(this, arrayOfMatch);
        ListView leagueMatchTableListView = (ListView) findViewById(R.id.leagueMatchTableListView);
        leagueMatchTableListView.setAdapter(matchTableAdapter);

        Button goToPointTableButton = (Button) findViewById(R.id.goToPointTableButton);
        goToPointTableButton.setOnClickListener(new Button.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Intent i = new Intent(getApplicationContext(), pointTable.class);
                                                        calculatePointTable();
                                                        startActivityForResult(i, 0);
                                                    }
                                                }
        );
    }

    public void calculatePointTable() {

        HashMap<String, player> arrayOfPlayers;
        arrayOfPlayers = pdbHandler.getPlayersInHashMap();
        for (Map.Entry<String, player> entry : arrayOfPlayers.entrySet()) {
            entry.getValue().reset();
        }

        final ListView matchListView = (ListView) findViewById(R.id.leagueMatchTableListView);
        match theMatch;

        for (int item = 0; item < matchListView.getAdapter().getCount(); item++) {

            theMatch = (match) matchListView.getAdapter().getItem(item);
            int homeGoal = theMatch.get_homeGoal();
            int guestGoal = theMatch.get_guestGoal();
            String home1 = theMatch.get_home1();
            String home2 = theMatch.get_home2();
            String guest1 = theMatch.get_guest1();
            String guest2 = theMatch.get_guest2();

            if ((homeGoal != -1) && (guestGoal != -1)) {
                if (homeGoal == guestGoal) {
                    arrayOfPlayers.get(home1).matchDraw();
                    arrayOfPlayers.get(home2).matchDraw();
                    arrayOfPlayers.get(guest1).matchDraw();
                    arrayOfPlayers.get(guest2).matchDraw();
                } else if (homeGoal > guestGoal) {
                    arrayOfPlayers.get(home1).matchWin((homeGoal - guestGoal));
                    arrayOfPlayers.get(home2).matchWin((homeGoal - guestGoal));
                    arrayOfPlayers.get(guest1).matchLoss((homeGoal - guestGoal));
                    arrayOfPlayers.get(guest2).matchLoss((homeGoal - guestGoal));
                } else {
                    arrayOfPlayers.get(home1).matchLoss((guestGoal - homeGoal));
                    arrayOfPlayers.get(home2).matchLoss((guestGoal - homeGoal));
                    arrayOfPlayers.get(guest1).matchWin((guestGoal - homeGoal));
                    arrayOfPlayers.get(guest2).matchWin((guestGoal - homeGoal));
                }
            }
        }
        pdbHandler.deleteAll();
        pdbHandler.addPlayerHashMap(arrayOfPlayers);
    }
}
