package com.pragjan.leaguescheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class custom_matchTableAdapter extends ArrayAdapter<match> {
    // View lookup cache
    private static class ViewHolder {
        TextView matchNo;
        TextView home1;
        TextView home2;
        TextView guest1;
        TextView guest2;
    }
    public custom_matchTableAdapter(Context context, ArrayList<match> theMatch) {
        super(context, R.layout.custom_enterplayernamerow, theMatch);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        match theMatch = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_matchtablerow, parent, false);
            viewHolder.matchNo = (TextView) convertView.findViewById(R.id.matchNo);
            viewHolder.home1 = (TextView) convertView.findViewById(R.id.textView1);
            viewHolder.home2 = (TextView) convertView.findViewById(R.id.textView2);
            viewHolder.guest1 = (TextView) convertView.findViewById(R.id.textView3);
            viewHolder.guest2 = (TextView) convertView.findViewById(R.id.textView4);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String matchNoStr = "Match " + (position+1);

        viewHolder.home1.setText(theMatch.get_home1());
        viewHolder.home2.setText(theMatch.get_home2());
        viewHolder.guest1.setText(theMatch.get_guest1());
        viewHolder.guest2.setText(theMatch.get_guest2());
        return convertView;
    }
}

