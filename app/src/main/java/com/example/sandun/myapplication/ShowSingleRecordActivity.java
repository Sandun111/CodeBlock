package com.example.sandun.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowSingleRecordActivity extends AppCompatActivity {

    String IDholder;
    TextView id, code, description;
    SQLiteDatabase sqLiteDatabase;
    myDbAdapter dbHelper;
    Cursor cursor;
    Button Delete, Edit;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_record);

        id = (TextView) findViewById(R.id.textViewId);
        code = (TextView) findViewById(R.id.textViewCode);
        description = (TextView) findViewById(R.id.textViewDescription);

        Delete = (Button)findViewById(R.id.buttonDelete);
        Edit = (Button)findViewById(R.id.buttonEdit);

        dbHelper = new myDbAdapter(this);

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenSQLiteDataBase();

                AlertDialog diaBox = AskOption();
                diaBox.show();

                //Creating an alert dialog to confirm the deletion
                /*SQLiteDataBaseQueryHolder = "DELETE FROM "+dbHelper.TABLE_CODE+" WHERE id = "+IDholder+"";*/
                /*sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);
                sqLiteDatabase.close();*/

            }
        });

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),EditSingleRecordActivity.class);
                intent.putExtra("EditID", IDholder);
                startActivity(intent);
                finish();

            }
        });

    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.ic_action_warning)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        dbHelper.deleteCode(IDholder);
                        dialog.dismiss();
                        finish();

                        /*Intent myIntent = new Intent(ShowSingleRecordActivity.this, MainActivity.class);
                        startActivity(myIntent);*/
                    }

                })

                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }


    @Override
    protected void onResume() {
        OpenSQLiteDataBase();

        ShowSingleRecordInTextView();

        super.onResume();


    }

    public void ShowSingleRecordInTextView() {

        sqLiteDatabase = dbHelper.myhelper.getWritableDatabase();

        IDholder = getIntent().getStringExtra("ListViewClickedItemValue");

        cursor = dbHelper.findCode(IDholder);


/*
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + dbHelper.TABLE_CODE + " WHERE id = " + IDholder + "", null);
*/

        if (cursor.moveToFirst()) {

            do {
                id.setText(cursor.getString(0));
                code.setText(cursor.getString(1));
                description.setText(cursor.getString(2));
            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase(){

        sqLiteDatabaseObj = openOrCreateDatabase(dbHelper.myhelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }
}

