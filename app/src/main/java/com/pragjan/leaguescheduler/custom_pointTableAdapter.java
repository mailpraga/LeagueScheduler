package com.pragjan.leaguescheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class custom_pointTableAdapter extends ArrayAdapter<player> {
    private List<player> thePlayer;

    public custom_pointTableAdapter(Context context, List<player> thePlayer) {
        super(context, R.layout.custom_pointtablerow, thePlayer);
        this.thePlayer = thePlayer;
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
            viewHolder.draw = (TextView) convertView.findViewById(R.id.drawTextView);
            viewHolder.gDiff = (TextView) convertView.findViewById(R.id.goalDiffTextView);
            viewHolder.point = (TextView) convertView.findViewById(R.id.pointTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position % 2 == 0) {
            convertView.setBackgroundResource(R.drawable.list_background1);
        } else {
            convertView.setBackgroundResource(R.drawable.list_background2);
        }

        viewHolder.playerName.setText(thePlayer.get_name());
        viewHolder.matchPlayed.setText("" + thePlayer.get_matchPlayed());
        viewHolder.win.setText("" + thePlayer.get_win());
        viewHolder.loss.setText("" + thePlayer.get_loss());
        viewHolder.draw.setText("" + thePlayer.get_draw());
        viewHolder.gDiff.setText("" + thePlayer.get_goalDiff());
        viewHolder.point.setText("" + thePlayer.get_point());
        return convertView;
    }

    private class ViewHolder {
        TextView playerName;
        TextView matchPlayed;
        TextView win;
        TextView loss;
        TextView draw;
        TextView gDiff;
        TextView point;
    }
}