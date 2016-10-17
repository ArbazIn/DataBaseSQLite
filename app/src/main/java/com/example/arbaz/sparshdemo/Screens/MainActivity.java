package com.example.arbaz.sparshdemo.Screens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arbaz.sparshdemo.Adapter.ListDetailAdapter;
import com.example.arbaz.sparshdemo.Global.Constants;
import com.example.arbaz.sparshdemo.Model.DetailFields;
import com.example.arbaz.sparshdemo.R;
import com.example.arbaz.sparshdemo.SQLiteHelper;
import com.example.arbaz.sparshdemo.Screens.AddDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    ListDetailAdapter listDetailAdapter;
    List<DetailFields> detailFieldList;
    DetailFields detailFields;
    Toolbar toolbar;
    TextView tb_txt_tv, empty_view;
    ImageView tb_back_iv;
    ListView lv_details;
    Button btn_add_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detailFieldList = new ArrayList<>();
        bindHere();
        setOnClickHere();
        ShowDBdata();

    }

    public void bindHere() {
        //ToolBar Setting
        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        tb_txt_tv = (TextView) toolbar.findViewById(R.id.tb_txt_tv);
        tb_back_iv = (ImageView) toolbar.findViewById(R.id.tb_back_iv);
        tb_txt_tv.setText("Home");
        toolbarBackButton(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //End ToolBar Setting


        lv_details = (ListView) findViewById(R.id.lv_details);
        empty_view = (TextView) findViewById(R.id.empty_view);
        btn_add_details = (Button) findViewById(R.id.btn_add_details);
        lv_details.setEmptyView(empty_view);

        SQLITEHELPER = new SQLiteHelper(this);


    }

    public void setOnClickHere() {
        btn_add_details.setOnClickListener(this);
    }

    public void toolbarBackButton(boolean img) {
        if (img == true) {
            tb_back_iv.setVisibility(View.VISIBLE);
        } else {
            tb_back_iv.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_details:
                Intent iAdd = new Intent(getApplicationContext(), AddDetailsActivity.class);
                startActivityForResult(iAdd, 1);

                break;
            default:
                break;
        }

    }

    @Override
    protected void onResume() {

        //ShowDBdata();

        super.onResume();
    }

    private void ShowDBdata() {

        SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();

        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM " + Constants.TABLE_NAME + "", null);


        if (cursor.moveToFirst()) {
            do {
                detailFields = new DetailFields(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)), cursor.getString(cursor.getColumnIndex(Constants.KEY_Name1)), cursor.getString(cursor.getColumnIndex(Constants.KEY_Name2)), cursor.getString(cursor.getColumnIndex(Constants.KEY_Name3)), cursor.getString(cursor.getColumnIndex(Constants.KEY_Name4)), cursor.getString(cursor.getColumnIndex(Constants.KEY_Name5)), cursor.getString(cursor.getColumnIndex(Constants.KEY_Name6)));
                detailFieldList.add(detailFields);


            } while (cursor.moveToNext());
        }

        listDetailAdapter = new ListDetailAdapter(detailFieldList, MainActivity.this);
        lv_details.setAdapter(listDetailAdapter);

        cursor.close();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        detailFieldList = new ArrayList<>();
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                ShowDBdata();

            }

        }
        if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
        }
    }

}
