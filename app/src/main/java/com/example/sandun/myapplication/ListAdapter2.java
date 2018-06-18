package com.example.sandun.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter2 extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> Number;
    ArrayList<String> Time;
    ArrayList<String> SentCode;

    public ListAdapter2(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> number,
            ArrayList<String> time,
            ArrayList<String> sentcode
    )
    {
        this.context = context2;
        this.ID = id;
        this.Number = number;
        this.Time = time;
        this.SentCode = sentcode;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.number_list, null);

            holder = new Holder();

            holder.ID_TextView = (TextView) child.findViewById(R.id.textViewID);
            holder.Number_TextView = (TextView) child.findViewById(R.id.textViewNumber);
            holder.Time_TextView = (TextView) child.findViewById(R.id.textViewtime);
            holder.SentCode_TextView = (TextView) child.findViewById(R.id.textViewSentCode);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.ID_TextView.setText(ID.get(position));
        holder.Number_TextView.setText(Number.get(position));
        holder.Time_TextView.setText(Time.get(position));
        holder.SentCode_TextView.setText(SentCode.get(position));

        return child;
    }

    public class Holder {

        TextView ID_TextView;
        TextView Number_TextView;
        TextView Time_TextView;
        TextView SentCode_TextView;
    }
}

