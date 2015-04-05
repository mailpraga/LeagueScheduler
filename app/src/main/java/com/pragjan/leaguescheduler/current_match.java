package com.pragjan.leaguescheduler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class current_match extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_match);

        final matchDBHandler mdbHandler = matchDBHandler.getInstance(this);

        Bundle enterPlayerActivity = getIntent().getExtras();
        if (enterPlayerActivity == null) {
            return;
        }

        final int TotalNumberOfMatch = enterPlayerActivity.getInt("TotalNumberOfMatch");
        final List<match> arrayOfMatch = mdbHandler.getMatchFromMatchNo(1);

        final custom_matchTableAdapter matchTableAdapter = new custom_matchTableAdapter(this, arrayOfMatch);
        final ListView currentMatchListView = (ListView) findViewById(R.id.currentMatchListView);
        currentMatchListView.setAdapter(matchTableAdapter);

        final Button getNextMatch = (Button) findViewById(R.id.getNextMatch);
        getNextMatch.setOnClickListener(new Button.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                EditText homeGoal = (EditText) findViewById(R.id.homeGoal);
                                                EditText guestGoal = (EditText) findViewById(R.id.guestGoal);
                                                String strHomeValue = homeGoal.getText().toString();
                                                String strGuestValue = guestGoal.getText().toString();
                                                if (strHomeValue.isEmpty() && strGuestValue.isEmpty()) {
                                                    // Do nothing
                                                } else {
                                                    int previousMatchNo = Integer.valueOf(((TextView) findViewById(R.id.matchNoTextView)).getText().toString());
                                                    if (TotalNumberOfMatch == previousMatchNo) {
                                                        getNextMatch.setText("League Finish");
                                                        getNextMatch.setClickable(false);
                                                    } else {
                                                        mdbHandler.setScoreForMatchNo(previousMatchNo, Integer.valueOf(strHomeValue), Integer.valueOf(strGuestValue));
                                                        List<match> arrayOfMatch = mdbHandler.getMatchFromMatchNo(previousMatchNo + 1);

                                                        custom_matchTableAdapter matchTableAdapter =
                                                                new custom_matchTableAdapter(v.getContext(), arrayOfMatch);

                                                        currentMatchListView.setAdapter(matchTableAdapter);
                                                        matchTableAdapter.notifyDataSetChanged();
                                                    }
                                                }
                                            }
                                        }
        );
    }
}
