package com.pragjan.leaguescheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;

public class custom_enterplayernameAdapter extends ArrayAdapter<String> {
private String[] food;
    public custom_enterplayernameAdapter(Context context, String[] food) {
        super(context, R.layout.custom_enterplayernamerow, food);
        this.food = food;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (food != null && food.length != 0) {
            return food.length;
        }
        return 0;
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return food[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ViewHolder holder = null;
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater pragaInflater = LayoutInflater.from(getContext());
            convertView = pragaInflater.inflate(R.layout.custom_enterplayernamerow, parent, false);
            holder.enterPlayerNameEditText = (EditText) convertView.findViewById(R.id.enterPlayerNameEditText);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ref = position;

        String playerNumber = getItem(position);
        holder.enterPlayerNameEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                food[holder.ref] = arg0.toString();
            }
        });
        return convertView;
    }

    private class ViewHolder {
        EditText enterPlayerNameEditText;
        int ref;
    }
}
