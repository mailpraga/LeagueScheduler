package com.pragjan.leaguescheduler;

import android.app.Activity ;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class enterPlayerName extends Activity  {
    private Spinner numPlayerSpinner;
    private playerDBHandler pdbHandler;
    private matchDBHandler mdbHandler;
    private int numPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_player_name);
        numPlayerSpinner = (Spinner) findViewById(R.id.numPlayerSpinner);
        addEntriesToNumPlayerSpinner();
        numPlayerSpinner.setOnItemSelectedListener(new CustomnumPlayerSpinnerOnItemSelectedListener(this));

        pdbHandler = playerDBHandler.getInstance(this);
        mdbHandler = matchDBHandler.getInstance(this);
    }

    public void addEntriesToNumPlayerSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Number_array, android.R.layout.simple_list_item_single_choice);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        // Apply the adapter to the spinner
        numPlayerSpinner.setAdapter(adapter);
    }

    public void onClickGenerate(View v)  {
        Intent i = new Intent(this, leagueMatchTable.class);
        numPlayer = Integer.parseInt(numPlayerSpinner.getSelectedItem().toString());
        i.putExtra("numPlayer", numPlayer);

        int TotalNumberOfMatch = numPlayer*(numPlayer-1)/2;
        i.putExtra("TotalNumberOfMatch", numPlayer);

        final ListView playerNameListView = (ListView) findViewById(R.id.enterPlayerNameListView);
        String playerName;
        View playerNameView;
        player thePlayer;
        List<player> playerNameList  = new ArrayList<player>();

        pdbHandler.deleteAll();
        for(int item = 0; item < playerNameListView.getAdapter().getCount(); item++){
            playerNameView = playerNameListView.getChildAt(item);
            playerName = ((EditText)playerNameView.findViewById(R.id.enterPlayerNameEditText)).getText().toString();
            thePlayer = new player(playerName,numPlayer);
            playerNameList.add(thePlayer);
            pdbHandler.addPlayer(thePlayer);
        }
        scheduler(TotalNumberOfMatch, playerNameList);
        startActivity(i);
    }

    public void scheduler(int TotalNumberOfMatch, List<player> playerNameList){
        mdbHandler.deleteAll();
        int count1, count2, count3;
        int count4 = 0;
        boolean partner1, partner2;
        int tmp1, tmp2;
        match theMatch;
        for(int j = 1; j <= TotalNumberOfMatch; j++) {
            partner1 = true;
            count1 = outerCheck(count4);
            count2 = count1;
            tmp1 = 0;
            while (partner1) {
                count2 = interCheck(count2);
                partner1 = playerNameList.get(count1).findMatchPartner(playerNameList.get(count2));
                if (tmp1 == numPlayer) {
                    count1 = interCheck(count1);
                    tmp1 = 0;
                }else {
                    tmp1 = tmp1 + 1;
                }
            }
            playerNameList.get(count1).appendMatchPartner(playerNameList.get(count2).get_name());
            playerNameList.get(count2).appendMatchPartner(playerNameList.get(count1).get_name());

            partner2 = true;
            count3 = outerCheck(count2);
            count4 = count3;
            tmp2 = 0;

            while (partner2){
                count4 = interCheck(count4);
                if ((playerNameList.get(count3) != playerNameList.get(count1)) &&
                        playerNameList.get(count4) != playerNameList.get(count1) &&
                        playerNameList.get(count3) != playerNameList.get(count2) &&
                        playerNameList.get(count4) != playerNameList.get(count2) ) {
                    partner2 = playerNameList.get(count3).findMatchPartner(playerNameList.get(count4));
                } else{
                    if (tmp2 == numPlayer) {
                        count3 = interCheck(count3);
                        tmp2 = 0;
                    }else {
                        tmp2 = tmp2 + 1;
                    }
                }
            }

            playerNameList.get(count3).appendMatchPartner(playerNameList.get(count4).get_name());
            playerNameList.get(count4).appendMatchPartner(playerNameList.get(count3).get_name());

            theMatch = new match(playerNameList.get(count1).get_name(),playerNameList.get(count2).get_name(),
                    playerNameList.get(count3).get_name(), playerNameList.get(count4).get_name());
            mdbHandler.addMatch(theMatch);
        }
    }

    public int outerCheck(int in){
        if(in == (numPlayer - 1)){
            return 0;
        } else {
            return in + 1;
        }
    }

    public int interCheck(int in){
        if(in == (numPlayer - 1)){
            return 0;
        } else {
            return in + 1;
        }
    }
}
