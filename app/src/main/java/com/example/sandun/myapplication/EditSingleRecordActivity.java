package com.example.sandun.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSingleRecordActivity extends AppCompatActivity {

    EditText code, description;
    Button update;
    SQLiteDatabase sqLiteDatabase;
    myDbAdapter dbHelper;
    Cursor cursor;
    String IDholder;
    String SQLiteDataBaseQueryHolder ;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_record);

        code = (EditText) findViewById(R.id.et_newCode);
        description = (EditText) findViewById(R.id.et_newDescription);

        update = (Button) findViewById(R.id.button_UpdateCode);

        dbHelper = new myDbAdapter(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String GetCode = code.getText().toString();
                String GetDescription = description.getText().toString();

                OpenSQLiteDataBase();

                dbHelper.updateCode(IDholder,GetCode, GetDescription);

                /*sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
                sqLiteDatabase.close();*/

                Toast.makeText(EditSingleRecordActivity.this,"Data Edit Successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(EditSingleRecordActivity.this, ShowSingleRecordActivity.class);

                intent.putExtra("ListViewClickedItemValue", IDholder);

                startActivity(intent);


            }
        });

    }

    @Override
    protected void onResume() {

        ShowSRecordInEditText();
        super.onResume();
    }

    public void ShowSRecordInEditText() {

        /*sqLiteDatabase = myDbHelper.getWritableDatabase();*/
        IDholder = getIntent().getStringExtra("EditID");
        cursor = dbHelper.findCode(IDholder);
/*
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + dbHelper.TABLE_CODE + " WHERE id = " + IDholder + "", null);
*/

        if (cursor.moveToFirst()) {

            do {
                code.setText(cursor.getString(1));
                description.setText(cursor.getString(2));
            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase(){

        sqLiteDatabaseObj = openOrCreateDatabase(myDbAdapter.myDbHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }
}
