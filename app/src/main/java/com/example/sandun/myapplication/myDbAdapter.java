package com.example.sandun.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertCode(String code, String desc)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.CODE, code);
        contentValues.put(myDbHelper.DESCRIPTION, desc);
        long id = dbb.insert(myDbHelper.TABLE_CODE, null , contentValues);
        return id;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.ID, myDbHelper.CODE, myDbHelper.DESCRIPTION};
        Cursor cursor = db.query(myDbHelper.TABLE_CODE,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();

        buffer.append("ID      CODE      DESCRIPTION "+ "\n");

        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.ID));
            String code =cursor.getString(cursor.getColumnIndex(myDbHelper.CODE));
            String  description =cursor.getString(cursor.getColumnIndex(myDbHelper.DESCRIPTION));
            buffer.append(cid+ "         " + code + "          " + description +" \n");
        }
        return cursor;
    }

    public  int deleteCode(String codeId)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={codeId};

        int count =db.delete(myDbHelper.TABLE_CODE ,myDbHelper.ID+" = ?",whereArgs);
        return  count;
    }

    public void updateCode(String id , String code,String description)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.CODE,code);
        contentValues.put(myDbHelper.DESCRIPTION,description);
        String[] whereArgs= {id};
        db.update(myDbHelper.TABLE_CODE,contentValues, myDbHelper.ID+" = ?",whereArgs );

    }



    public String getRandomCode()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.CODE};
        Cursor cursor =db.query(myDbHelper.TABLE_CODE,columns,null,null,null,null,"RANDOM()" , "1");
        StringBuffer buffer= new StringBuffer();

        while (cursor.moveToNext())
        {
            String code =cursor.getString(cursor.getColumnIndex(myDbHelper.CODE));
            /*buffer.append("Thank you for calling..!\n Code   " + code +  "\n");*/
            buffer.append(code);
        }
        return buffer.toString();

        /*String code = cursor.getString(cursor.getColumnIndex(myDbHelper.CODE));*/
    }

    public Cursor findCode(String id)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.ID, myDbHelper.CODE, myDbHelper.DESCRIPTION};
        /*String[] whereArgs= {id};*/
        String CID = id;

        Cursor cursor = db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_CODE + " WHERE _id = " + CID + "", null);

/*
        Cursor cursor =db.query(TABLE_CODE,columns,ID+" =?",whereArgs,null,null,null);
*/


        /*String query = "Select * FROM " + TABLE_CODE + "WHERE" + ID +"= '"+id + "'";
        Cursor cursor = db.rawQuery(query,null);*/
        return cursor;
    }

    public long insertNumber(String number, String time, String sentcode)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NUMBER, number);
        contentValues.put(myDbHelper.TIME, time);
        contentValues.put(myDbHelper.SENTCODE, sentcode);
        long id = dbb.insert(myDbHelper.TABLE_NUMBER, null , contentValues);
        return id;
    }

    public Cursor getNumbers()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.ID,myDbHelper.NUMBER,myDbHelper.TIME,myDbHelper.SENTCODE};
        Cursor cursor =db.query(myDbHelper.TABLE_NUMBER,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();

        buffer.append("ID        NUMBER          TIME "+ "\n");

        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.ID));
            String number =cursor.getString(cursor.getColumnIndex(myDbHelper.NUMBER));
            String  time =cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
            String  sentcode =cursor.getString(cursor.getColumnIndex(myDbHelper.SENTCODE));
            buffer.append(cid+ "         " + number + "          " + time + "       " +sentcode+" \n");
        }
        return cursor;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        // Database Version
        private static final int DATABASE_Version = 1;

        // Database Name
        public static final String DATABASE_NAME = "smsApp";

        // Table Names
        private static final String TABLE_CODE = "code";
        private static final String TABLE_NUMBER = "numbers";

        // Common column names
        private static final String ID="_id";     // Column I (Primary Key)

        // code Table - column names
        private static final String CODE = "Code";    //Column II
        private static final String DESCRIPTION = "Description";    // Column III

        // numbers Table - column names
        private static final String NUMBER = "Number";    //Column II
        private static final String TIME = "Time"; //Column III
        private static final String SENTCODE = "SentCode";// Column IV

        private static final String CREATE_TABLE_NUMBER = "CREATE TABLE "+TABLE_NUMBER+
                " ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NUMBER+" VARCHAR(255) ,"+ TIME+" VARCHAR(225),"+SENTCODE+" VARCHAR(255));";
        private static final String DROP_TABLE_NUMBER ="DROP TABLE IF EXISTS "+TABLE_NUMBER;

        private static final String CREATE_TABLE_CODE = "CREATE TABLE "+TABLE_CODE+
                " ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CODE+" VARCHAR(255) ,"+ DESCRIPTION+" VARCHAR(225));";
        private static final String DROP_TABLE_CODE ="DROP TABLE IF EXISTS "+TABLE_CODE;


        private Context context;


        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE_CODE);
                db.execSQL(CREATE_TABLE_NUMBER);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE_CODE);
                db.execSQL(DROP_TABLE_NUMBER);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}

