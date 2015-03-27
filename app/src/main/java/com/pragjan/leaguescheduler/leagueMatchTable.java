package com.pragjan.leaguescheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class leagueMatchTable extends Activity {

    private playerDBHandler pdbHandler;
    private matchDBHandler mdbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_match_table);

        pdbHandler = playerDBHandler.getInstance(this);
        mdbHandler = matchDBHandler.getInstance(this);

        Bundle enterPlayerActivity = getIntent().getExtras();
        if(enterPlayerActivity == null){
            return;
        }
        int TotalNumberOfMatch = enterPlayerActivity.getInt("TotalNumberOfMatch");

        List<match> arrayOfMatch = new ArrayList<match>();
        arrayOfMatch = mdbHandler.getMatches();

        ListAdapter matchTableAdapter = new custom_matchTableAdapter(this, arrayOfMatch);
        ListView leagueMatchTableListView = (ListView) findViewById(R.id.leagueMatchTableListView);
        leagueMatchTableListView.setAdapter(matchTableAdapter);
        /*
        final ListView matchListView = (ListView) findViewById(R.id.leagueMatchTableListView);
        View macthView;

        for(int item = 0; item < matchListView.getAdapter().getCount(); item++) {
            macthView = matchListView.getChildAt(item);
            final EditText hGoal = (EditText) macthView.findViewById(R.id.homeGoal);
            final EditText gGoal = (EditText) macthView.findViewById(R.id.guestGoal);
            final TextView p1 = (TextView) macthView.findViewById(R.id.textView1);
            final TextView p2 = (TextView) macthView.findViewById(R.id.textView2);
            final TextView p3 = (TextView) macthView.findViewById(R.id.textView3);
            final TextView p4 = (TextView) macthView.findViewById(R.id.textView4);

            hGoal.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String homeGoal = hGoal.getText().toString();
                        String guestGoal = gGoal.getText().toString();
                        if(!homeGoal.isEmpty() &&
                            (!guestGoal.isEmpty())){
                            p1.getText().toString()
                            pdbHandler.
                        }
                    }
                }
            });
            */
        }

    public void goToPointTable(View v) {
        Intent i = new Intent(this, pointTable.class);

        List<player> arrayOfPlayers = new ArrayList<player>();
        arrayOfPlayers = pdbHandler.getPlayers();

        ListView matchListView = (ListView) findViewById(R.id.leagueMatchTableListView);
        View matchView;

        for(int item = 0; item < matchListView.getAdapter().getCount(); item++) {
            matchView = matchListView.getChildAt(item);
            EditText hGoal = (EditText) matchView.findViewById(R.id.homeGoal);
            EditText gGoal = (EditText) matchView.findViewById(R.id.guestGoal);
            TextView p1 = (TextView) matchView.findViewById(R.id.textView1);
            TextView p2 = (TextView) matchView.findViewById(R.id.textView2);
            TextView p3 = (TextView) matchView.findViewById(R.id.textView3);
            TextView p4 = (TextView) matchView.findViewById(R.id.textView4);

            String homeGoal = hGoal.getText().toString();
            String guestGoal = gGoal.getText().toString();
            if(!homeGoal.isEmpty() && (!guestGoal.isEmpty())) {
                if(Integer.valueOf(homeGoal) == Integer.valueOf(guestGoal)){

                }else if(Integer.valueOf(homeGoal) > Integer.valueOf(guestGoal)) {

                }else {

                }
            }
        }

        startActivity(i);
    }

}
