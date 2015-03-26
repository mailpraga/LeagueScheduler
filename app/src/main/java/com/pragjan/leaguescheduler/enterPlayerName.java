package com.pragjan.leaguescheduler;

import android.app.Activity ;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;


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
                R.array.Number_array, android.R.layout.simple_dropdown_item_1line);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
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

        pdbHandler.deleteAll();
        for(int item = 0; item < playerNameListView.getAdapter().getCount(); item++){
            View vplayerNameListView = playerNameListView.getChildAt(item);
            playerName = ((EditText)vplayerNameListView.findViewById(R.id.enterPlayerNameEditText)).getText().toString();
            player thePlayer = new player(playerName,numPlayer);
            pdbHandler.addPlayer(thePlayer);
        }
        scheduler(TotalNumberOfMatch);
        startActivity(i);
    }

    public void scheduler(int TotalNumberOfMatch){
        mdbHandler.deleteAll();
        int count1;
        int count2;
        int count3;
        int count4 = 1;
        boolean partner1;
        boolean partner2;
        int tmp;
        for(int j = 1; j < TotalNumberOfMatch; j++) {
            partner1 = true;
            count1 = outerCheck(count4);
            count2 = count1;

            while (partner1) {
                count2 = interCheck(count2);
                partner1 = pdbHandler.findMatchPartner(count1, count2);
            }
            pdbHandler.appendMatchPartner(count1,count2);
            pdbHandler.appendMatchPartner(count2,count1);

            partner2 = true;
            count3 = outerCheck(count2);
            count4 = count3;
            tmp = 0;

            while (partner2){
                count4 = interCheck(count4);

                if ((pdbHandler.getName(count3) != pdbHandler.getName(count1)) &&
                        pdbHandler.getName(count4) != pdbHandler.getName(count1) &&
                        pdbHandler.getName(count3) != pdbHandler.getName(count2) &&
                        pdbHandler.getName(count4) != pdbHandler.getName(count2) ) {
                    partner2 = pdbHandler.findMatchPartner(count3, count4);
                } else{
                    if (tmp == numPlayer) {
                        count3 = interCheck(count3);
                    }else {
                        tmp = tmp + 1;
                    }
                }
            }

            pdbHandler.appendMatchPartner(count3, count4);
            pdbHandler.appendMatchPartner(count4, count3);

            match theMatch = new match(pdbHandler.getName(count1),pdbHandler.getName(count2),
                                       pdbHandler.getName(count3),pdbHandler.getName(count4));
            mdbHandler.addMatch(theMatch);
        }
    }

    public int outerCheck(int in){
        if(in == numPlayer){
            return 1;
        } else {
            return in + 1;
        }
    }

    public int interCheck(int in){
        if(in == numPlayer){
            return 1;
        } else {
            return in + 1;
        }
    }
}
