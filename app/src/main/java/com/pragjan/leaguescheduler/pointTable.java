package com.pragjan.leaguescheduler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class pointTable extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_table);

        playerDBHandler pdbHandler = playerDBHandler.getInstance(this);

        List<player> arrayOfPlayers = pdbHandler.getPlayersInList();

        custom_pointTableAdapter pointTableAdapter = new custom_pointTableAdapter(this, arrayOfPlayers);
        ListView pointTableListView = (ListView) findViewById(R.id.pointTableListView);
        pointTableListView.setAdapter(pointTableAdapter);

        Button goToLeagueTableButton = (Button) findViewById(R.id.goToLeagueTableButton);
        goToLeagueTableButton.setOnClickListener(new Button.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        finish();
                                                    }
                                                }
        );
    }
}
