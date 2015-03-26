package com.pragjan.leaguescheduler;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomnumPlayerSpinnerOnItemSelectedListener implements OnItemSelectedListener {
    int check=0;
    private Activity m_activity = null;
    public CustomnumPlayerSpinnerOnItemSelectedListener (Activity activity)
    {
        m_activity = activity;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        check = check+1;
        if(check>1) {
            String numberOfPlayer = parent.getItemAtPosition(pos).toString();
            List<String> numOfPlayerArray = new ArrayList<String>();
            numOfPlayerArray.add("1");
            numOfPlayerArray.add("2");
            numOfPlayerArray.add("3");
            numOfPlayerArray.add("4");
            switch (numberOfPlayer) {
                case "5":
                    numOfPlayerArray.add("5");
                    break;
                case "6":
                    numOfPlayerArray.add("5");
                    numOfPlayerArray.add("6");
                    break;
                case "7":
                    numOfPlayerArray.add("5");
                    numOfPlayerArray.add("6");
                    numOfPlayerArray.add("7");
                    break;
                case "8":
                    numOfPlayerArray.add("5");
                    numOfPlayerArray.add("6");
                    numOfPlayerArray.add("7");
                    numOfPlayerArray.add("8");
                case "9":
                    numOfPlayerArray.add("5");
                    numOfPlayerArray.add("6");
                    numOfPlayerArray.add("7");
                    numOfPlayerArray.add("8");
                    numOfPlayerArray.add("9");
                    break;
                case "10":
                    numOfPlayerArray.add("5");
                    numOfPlayerArray.add("6");
                    numOfPlayerArray.add("7");
                    numOfPlayerArray.add("8");
                    numOfPlayerArray.add("9");
                    numOfPlayerArray.add("10");
                    break;
                default:
            }

            ListAdapter enterplayernameAdapter = new custom_enterplayernameAdapter(view.getContext(),
                    numOfPlayerArray.toArray(new String[numOfPlayerArray.size()]));
            ListView enterPlayerNameListView = (ListView) m_activity.findViewById(R.id.enterPlayerNameListView);
            enterPlayerNameListView.setAdapter(enterplayernameAdapter);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}