package com.pragjan.leaguescheduler;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class custom_matchTableAdapter extends ArrayAdapter<match> {
    private List<match> theMatchList;

    public custom_matchTableAdapter(Context context, List<match> theMatch) {
        super(context, R.layout.custom_enterplayernamerow, theMatch);
        this.theMatchList = theMatch;
    }

    @Override
    public int getCount() {
        return theMatchList.size();
    }

    @Override
    public match getItem(int position) {
        return theMatchList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final match theMatch = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_matchtablerow, parent, false);
            viewHolder.matchNo = (TextView) convertView.findViewById(R.id.matchNo);
            viewHolder.home1 = (TextView) convertView.findViewById(R.id.textView1);
            viewHolder.home2 = (TextView) convertView.findViewById(R.id.textView2);
            viewHolder.homeGoal = (EditText) convertView.findViewById(R.id.homeGoal);
            viewHolder.guest1 = (TextView) convertView.findViewById(R.id.textView3);
            viewHolder.guest2 = (TextView) convertView.findViewById(R.id.textView4);
            viewHolder.guestGoal = (EditText) convertView.findViewById(R.id.guestGoal);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ref = position;

        String matchNoStr = "Match " + (position+1);
        viewHolder.matchNo.setText(matchNoStr);
        viewHolder.home1.setText(theMatch.get_home1());
        viewHolder.home2.setText(theMatch.get_home2());
        viewHolder.guest1.setText(theMatch.get_guest1());
        viewHolder.guest2.setText(theMatch.get_guest2());

        viewHolder.homeGoal.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void afterTextChanged(Editable arg0){
                String value = arg0.toString();
                if(value != null) {
                    theMatchList.get(viewHolder.ref).set_homeGoal(Integer.valueOf(value));
                }
            }
        });

        viewHolder.guestGoal.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void afterTextChanged(Editable arg0){
                String value = arg0.toString();
                if(value != null) {
                    theMatchList.get(viewHolder.ref).set_guestGoal(Integer.valueOf(value));
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView matchNo;
        TextView home1;
        TextView home2;
        TextView guest1;
        TextView guest2;
        EditText homeGoal;
        EditText guestGoal;
        int ref;
    }
}

