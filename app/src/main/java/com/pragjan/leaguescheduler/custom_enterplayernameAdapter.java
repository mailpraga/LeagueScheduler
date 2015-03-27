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
private String[] PlayerName;
    public custom_enterplayernameAdapter(Context context, String[] PlayerName) {
        super(context, R.layout.custom_enterplayernamerow, PlayerName);
        this.PlayerName = PlayerName;
    }

    @Override
    public int getCount() {
       return PlayerName.length;
    }

    public void setListData(String[] data){
        PlayerName = data;
    }


    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return PlayerName[position];
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

        PlayerName[position] = "Player" + (position+1);
        holder.enterPlayerNameEditText.setText(PlayerName[position]);

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
                PlayerName[holder.ref] = arg0.toString();
            }
        });
        return convertView;
    }

    private class ViewHolder {
        EditText enterPlayerNameEditText;
        int ref;
    }
}
