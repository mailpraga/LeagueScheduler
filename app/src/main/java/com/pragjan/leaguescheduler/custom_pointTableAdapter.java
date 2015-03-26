package com.pragjan.leaguescheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class custom_pointTableAdapter extends ArrayAdapter<player> {
    // View lookup cache
    private static class ViewHolder {
        TextView playerName;
        TextView matchPlayed;
        TextView win;
        TextView loss;
        TextView gDiff;
        TextView point;
    }

    public custom_pointTableAdapter(Context context, ArrayList<player> thePlayer) {
        super(context, R.layout.custom_pointtablerow, thePlayer);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        player thePlayer = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_pointtablerow, parent, false);

            viewHolder.playerName = (TextView) convertView.findViewById(R.id.playerNameTextView);
            viewHolder.matchPlayed = (TextView) convertView.findViewById(R.id.matchPlayedTextView);
            viewHolder.win = (TextView) convertView.findViewById(R.id.winTextView);
            viewHolder.loss = (TextView) convertView.findViewById(R.id.lossTextView);
            viewHolder.gDiff = (TextView) convertView.findViewById(R.id.goalDiffTextView);
            viewHolder.point = (TextView) convertView.findViewById(R.id.pointTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.playerName.setText(thePlayer.get_name());
        viewHolder.matchPlayed.setInputType(thePlayer.get_matchPlayed());
        viewHolder.win.setInputType(thePlayer.get_win());
        viewHolder.loss.setInputType(thePlayer.get_loss());
        viewHolder.gDiff.setText("");
        viewHolder.point.setInputType(thePlayer.get_point());
        return convertView;
    }
}