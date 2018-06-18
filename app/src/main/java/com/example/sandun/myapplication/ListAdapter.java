package com.example.sandun.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> Code;
    ArrayList<String> Description;

    public ListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> code,
            ArrayList<String> description
    )
    {
        this.context = context2;
        this.ID = id;
        this.Code = code;
        this.Description = description;
    }

    public void refreshEvents(ArrayList<String> i, ArrayList<String> j, ArrayList<String> k) {
        this.ID.clear();
        this.Code.clear();
        this.Description.clear();
        this.ID.addAll(i);
        this.Code.addAll(j);
        this.Description.addAll(k);
        notifyDataSetChanged();
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

            child = layoutInflater.inflate(R.layout.code_list, null);

            holder = new Holder();

            holder.ID_TextView = (TextView) child.findViewById(R.id.textViewID);
            holder.Code_TextView = (TextView) child.findViewById(R.id.textViewNumber);
            holder.Description_TextView = (TextView) child.findViewById(R.id.textViewtime);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.ID_TextView.setText(ID.get(position));
        holder.Code_TextView.setText(Code.get(position));
        holder.Description_TextView.setText(Description.get(position));

        return child;
    }

    public class Holder {

        TextView ID_TextView;
        TextView Code_TextView;
        TextView Description_TextView;
    }
}

