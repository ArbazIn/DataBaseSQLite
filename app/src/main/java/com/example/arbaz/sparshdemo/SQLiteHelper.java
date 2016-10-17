package com.example.arbaz.sparshdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.arbaz.sparshdemo.Global.Constants;

/**
 * Created by arbaz on 14/10/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + " (" + Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Constants.KEY_Name1 + " VARCHAR, " + Constants.KEY_Name2 + " VARCHAR, " + Constants.KEY_Name3 + " VARCHAR," + Constants.KEY_Name4 + " VARCHAR," + Constants.KEY_Name5 + " VARCHAR," + Constants.KEY_Name6 + " VARCHAR)";
        database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
        onCreate(db);
    }
}
