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
            viewHolder.matchNo = (TextView) convertView.findViewById(R.id.matchNoTextView);
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

        if (position % 2 == 0) {
            convertView.setBackgroundResource(R.drawable.list_background1);
        } else {
            convertView.setBackgroundResource(R.drawable.list_background2);
        }
        viewHolder.ref = position;

        viewHolder.matchNo.setText(String.valueOf(theMatch.get_matchNo()));
        viewHolder.home1.setText(theMatch.get_home1());
        viewHolder.home2.setText(theMatch.get_home2());
        viewHolder.guest1.setText(theMatch.get_guest1());
        viewHolder.guest2.setText(theMatch.get_guest2());
        if (theMatch.get_homeGoal() == -1) {
            viewHolder.homeGoal.setText("");
        } else {
            viewHolder.homeGoal.setText(String.valueOf(theMatch.get_homeGoal()));
        }
        if (theMatch.get_guestGoal() == -1) {
            viewHolder.guestGoal.setText("");
        } else {
            viewHolder.guestGoal.setText(String.valueOf(theMatch.get_guestGoal()));
        }

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
                int IntValue;
                String StrValue = arg0.toString();
                if (!StrValue.isEmpty()) {
                    IntValue = Integer.valueOf(StrValue);
                } else {
                    IntValue = -1;
                }
                theMatchList.get(viewHolder.ref).set_homeGoal(IntValue);
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
                int IntValue;
                String StrValue = arg0.toString();
                if (!StrValue.isEmpty()) {
                    IntValue = Integer.valueOf(StrValue);
                } else {
                    IntValue = -1;
                }
                theMatchList.get(viewHolder.ref).set_guestGoal(IntValue);
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

