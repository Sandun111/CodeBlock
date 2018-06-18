package com.example.sandun.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewRecordActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabaseObj;
    myDbAdapter dbHelper = new myDbAdapter(this);
    EditText editTextCode, editTextDescription;
    String CodeHolder, DescriptionHolder, SQLiteDataBaseQueryHolder;
    Button EnterData;
    Boolean EditTextEmptyHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        EnterData = (Button)findViewById(R.id.button_AddCode);
        editTextCode = (EditText)findViewById(R.id.et_Code);
        editTextDescription = (EditText)findViewById(R.id.et_Description);

        EnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*SQLiteDataBaseBuild();*/
                /*SQLiteTableBuild();*/
                CheckEditTextStatus();
                InsertDataIntoSQLiteDatabase();
                EmptyEditTextAfterDataInsert();

            }
        });

    }

    /*public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(DatabaseHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }*/

    /*public void SQLiteTableBuild(){
        dbHelper.onCreate(sqLiteDatabaseObj);
*//*
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "+dbHelper.TABLE_CODE+"("+dbHelper.Table_Column_ID+" PRIMARY KEY AUTOINCREMENT NOT NULL, "+SQLiteHelper.Table_Column_Code+" VARCHAR, "+SQLiteHelper.Table_Column_Description+" VARCHAR);");
*//*
    }*/

    public void CheckEditTextStatus(){

        CodeHolder = editTextCode.getText().toString() ;
        DescriptionHolder = editTextDescription.getText().toString();

        if(TextUtils.isEmpty(CodeHolder) || TextUtils.isEmpty(DescriptionHolder)){
            EditTextEmptyHold = false ;
        }
        else {
            EditTextEmptyHold = true ;
        }
    }


    /*public void addCode(View view) {
        if(EditTextEmptyHold == true)
        {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            Code code = new Code(CodeHolder, DescriptionHolder);
            dbHelper.insertCode(code);
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            sqLiteDatabaseObj.close();
            Toast.makeText(AddNewRecordActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(AddNewRecordActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
        }

    }*/

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHold == true)
        {
            dbHelper.insertCode(CodeHolder, DescriptionHolder);
            /*sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            sqLiteDatabaseObj.close();*/
            /*sqLiteDatabaseObj.close();*/

            Toast.makeText(AddNewRecordActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();
            /*Intent myIntent = new Intent(AddNewRecordActivity.this, MainActivity.class);
            startActivity(myIntent);*/
        }

        else {
            Toast.makeText(AddNewRecordActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
        }
    }

    public void EmptyEditTextAfterDataInsert(){

        editTextCode.getText().clear();
        editTextDescription.getText().clear();

    }
}
