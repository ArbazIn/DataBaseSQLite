package com.example.arbaz.sparshdemo.Screens;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arbaz.sparshdemo.Global.Constants;
import com.example.arbaz.sparshdemo.R;

import java.util.ArrayList;

public class AddDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    TextView tb_txt_tv;
    ImageView tb_back_iv;
    SQLiteDatabase sparshDB;

    String query;
    Button btn_add_details;
    EditText et_fName, et_houseHolder, et_occHouseHolder, et_nameOfMember, et_age;
    RadioGroup genderGroup;
    RadioButton rb_male, rb_female;

    String gender_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        //DataBase Setting
        sparshDB = openOrCreateDatabase(Constants.DATABASE_NAME, Context.MODE_PRIVATE, null);
        sparshDB.execSQL("CREATE TABLE IF NOT EXISTS " + Constants.TABLE_NAME + "(" + Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Constants.KEY_Name1 + " VARCHAR," + Constants.KEY_Name2 + " VARCHAR," + Constants.KEY_Name3 + " VARCHAR," + Constants.KEY_Name4 + " INTEGER," + Constants.KEY_Name5 + " VARCHAR," + Constants.KEY_Name6 + " VARCHAR);");
        bindHere();
        setOnClickHere();
    }


    public void bindHere() {

        //ToolBar Setting
        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        tb_txt_tv = (TextView) toolbar.findViewById(R.id.tb_txt_tv);
        tb_back_iv = (ImageView) toolbar.findViewById(R.id.tb_back_iv);
        tb_txt_tv.setText("Add Details");
        toolbarBackButton(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //End ToolBar Setting


        et_fName = (EditText) findViewById(R.id.et_fName);
        et_houseHolder = (EditText) findViewById(R.id.et_houseHolder);
        et_occHouseHolder = (EditText) findViewById(R.id.et_occHouseHolder);
        et_age = (EditText) findViewById(R.id.et_age);
        et_nameOfMember = (EditText) findViewById(R.id.et_nameOfMember);

        genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_female = (RadioButton) findViewById(R.id.rb_female);


        btn_add_details = (Button) findViewById(R.id.btn_add_details);

    }

    public void setOnClickHere() {
        btn_add_details.setOnClickListener(this);
        tb_back_iv.setOnClickListener(this);
        et_age.setOnClickListener(this);
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
            case R.id.tb_back_iv:
                finish();
                break;
            case R.id.btn_add_details:

                int selectGenderId = genderGroup.getCheckedRadioButtonId();
                if (selectGenderId == R.id.rb_male) {
                    gender_txt = "Male";

                } else if (selectGenderId == R.id.rb_female) {
                    gender_txt = "Female";
                }
                if (isValid()) {
                    String fname = et_fName.getText().toString();
                    String hhname = et_houseHolder.getText().toString();
                    String ohh = et_occHouseHolder.getText().toString();
                    String age = et_age.getText().toString();
                    String gender = gender_txt;
                    String member = et_nameOfMember.getText().toString();

                    query = "INSERT INTO " + Constants.TABLE_NAME + "(" + Constants.KEY_Name1 + "," + Constants.KEY_Name2 + "," + Constants.KEY_Name3 + "," + Constants.KEY_Name4 + "," + Constants.KEY_Name5 + ",'" + Constants.KEY_Name6 + "')" + " VALUES('" + fname + "','" + hhname + "','" + ohh + "','" + age + "','" + gender + "','" + member + "');";
                    sparshDB.execSQL(query);

                    Toast.makeText(getApplicationContext(), "Record Inserted  Successfully!", Toast.LENGTH_LONG).show();


                    Intent retunIntent = new Intent();
                    setResult(Activity.RESULT_OK, retunIntent);
                    finish();
                }
                break;
            case R.id.et_age:
                Toolbar toolbar;
                TextView tb_txt_tv;
                final Dialog d = new Dialog(AddDetailsActivity.this, R.style.AppTheme);
                d.setContentView(R.layout.custom_number_picker_dialog);
                toolbar = (Toolbar) d.findViewById(R.id.custom_toolbar);
                tb_txt_tv = (TextView) toolbar.findViewById(R.id.tb_txt_tv);
                tb_txt_tv.setText("Select Age");
                Window window = d.getWindow();
                window.setLayout(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                Button b1 = (Button) d.findViewById(R.id.btn_set);
                Button b2 = (Button) d.findViewById(R.id.btn_cancel);
                final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
                np.setMaxValue(150);
                np.setMinValue(25);
                np.setWrapSelectorWheel(false);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        et_age.setText(String.valueOf(np.getValue()));
                        d.dismiss();
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });
                d.show();


                break;
            default:
                break;
        }

    }

    public boolean isValid() {
        boolean valid = true;


        String fname = et_fName.getText().toString();
        String hh = et_houseHolder.getText().toString();
        String ohh = et_occHouseHolder.getText().toString();
        String age = et_age.getText().toString();
        String member = et_nameOfMember.getText().toString();


        if (fname.trim().isEmpty()) {
            et_fName.setError(getResources().getString(R.string.m_f_name));
            valid = false;
        } else {
            et_fName.setError(null);
        }
        if (hh.trim().isEmpty()) {
            et_houseHolder.setError(getResources().getString(R.string.m_hoh));
            valid = false;
        } else {
            et_houseHolder.setError(null);
        }
        if (ohh.trim().isEmpty()) {
            et_occHouseHolder.setError(getResources().getString(R.string.m_ohh));
            valid = false;
        } else {
            et_occHouseHolder.setError(null);
        }
        if (age.trim().isEmpty()) {
            et_age.setError(getResources().getString(R.string.m_age));
            valid = false;
        } else {
            et_age.setError(null);
        }
        if (member.isEmpty()) {
            et_nameOfMember.setError(getResources().getString(R.string.m_nofm));
            valid = false;
        } else {
            et_nameOfMember.setError(null);
        }
        return valid;
    }
}
