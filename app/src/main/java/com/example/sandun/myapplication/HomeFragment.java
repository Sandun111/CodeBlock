package com.example.sandun.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    /*DatabaseHelper dbHelper;*/
    public static myDbAdapter dbHelper = null;
    public static SQLiteDatabase sqLiteDatabase = null;
    /*SQLiteDatabase sqLiteDatabase;*/
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView listView;

    ArrayList<String> ID_Array;
    ArrayList<String> CODE_Array;
    ArrayList<String> DESCRIPTION_Array;

    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();
    String TempHolder ;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = (ListView)view.findViewById(R.id.listView);
        ID_Array = new ArrayList<String>();
        CODE_Array = new ArrayList<String>();
        DESCRIPTION_Array = new ArrayList<String>();
        dbHelper = new myDbAdapter(this.getActivity());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                closefragment();
                getActivity().getFragmentManager().popBackStack();

                Intent intent = new Intent(getActivity(), ShowSingleRecordActivity.class);

                intent.putExtra("ListViewClickedItemValue", ListViewClickItemArray.get(position).toString());

                startActivity(intent);

            }
        });

        FloatingActionButton fab_add = (FloatingActionButton) view.findViewById(R.id.fabadd);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AddNewRecordActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }


    private void closefragment() {
        getActivity().getFragmentManager().popBackStack();
    }

    @Override
    public void onResume() {

        dbHelper = new myDbAdapter(getActivity());
        /*sqLiteDatabase = dbHelper.getWritableDatabase();*/

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        /*sqLiteDatabase = dbHelper.getWritableDatabase();*/

        cursor = dbHelper.getData();
/*
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_CODE+"", null);
*/
        ID_Array.clear();
        CODE_Array.clear();
        DESCRIPTION_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add((cursor.getString(0)));

                //Inserting Column ID into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(0));

                CODE_Array.add(cursor.getString(1));

                DESCRIPTION_Array.add(cursor.getString(2));


            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(getActivity(),

                ID_Array,
                CODE_Array,
                DESCRIPTION_Array
        );

        listView.setAdapter(listAdapter);
        cursor.close();
    }



}
