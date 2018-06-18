package com.example.sandun.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumlistFragment extends Fragment {

    myDbAdapter dbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter2 listAdapter ;
    ListView listView;

    ArrayList<String> ID_Array;
    ArrayList<String> NUMBER_Array;
    ArrayList<String> TIME_Array;
    ArrayList<String> SentCode_Array;

    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();
    String TempHolder ;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Numbers List");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_numlist, container, false);

        listView = (ListView)view.findViewById(R.id.listView2);
        ID_Array = new ArrayList<String>();
        NUMBER_Array = new ArrayList<String>();
        TIME_Array = new ArrayList<String>();
        SentCode_Array = new ArrayList<String>();
        dbHelper = new myDbAdapter(this.getActivity());


        return view;

    }

    @Override
    public void onResume() {

        dbHelper = new myDbAdapter(getActivity());
        ShowSQLiteDBdata() ;
        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        cursor = dbHelper.getNumbers();
/*
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_CODE+"", null);
*/
        ID_Array.clear();
        NUMBER_Array.clear();
        TIME_Array.clear();
        SentCode_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add((cursor.getString(0)));

                //Inserting Column ID into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(0));

                NUMBER_Array.add(cursor.getString(1));

                TIME_Array.add(cursor.getString(2));

                SentCode_Array.add(cursor.getString(3));


            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter2(getActivity(),

                ID_Array,
                NUMBER_Array,
                TIME_Array,
                SentCode_Array
        );

        listView.setAdapter(listAdapter);
        cursor.close();
    }

}
