package com.example.arbaz.sparshdemo.Screens;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.arbaz.sparshdemo.Model.DetailFields;
import com.example.arbaz.sparshdemo.R;

public class EditDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    String GetSQliteQuery, UpdateRecordQuery;

    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    Toolbar toolbar;
    TextView tb_txt_tv;
    ImageView tb_back_iv;

    EditText et_update_fName, et_update_houseHolder, et_update_occHouseHolder, et_update_age, et_update_nameOfMember;
    RadioGroup genderGroup_update;
    RadioButton rb_male_update, rb_female_update;
    Button btn_update_details;
    String update_gender_txt;
    String ConvertEmail;
    Bundle bDetails;
    DetailFields detailFields;
    int detail_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
        GetSQliteQuery = "SELECT * FROM " + Constants.TABLE_NAME + "";
        SQLITEDATABASE = openOrCreateDatabase(Constants.DATABASE_NAME, Context.MODE_PRIVATE, null);
      /*  cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);
        cursor.moveToFirst();*/

        bDetails = new Bundle(getIntent().getExtras());
        detailFields = (DetailFields) bDetails.get("EditDetails");

        bindHere();
        setOnClickHere();
        GetPreDatabaseRecords();
    }

    public void bindHere() {

        //ToolBar Setting
        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        tb_txt_tv = (TextView) toolbar.findViewById(R.id.tb_txt_tv);
        tb_back_iv = (ImageView) toolbar.findViewById(R.id.tb_back_iv);
        tb_txt_tv.setText("Update Details");
        toolbarBackButton(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //End ToolBar Setting


        et_update_fName = (EditText) findViewById(R.id.et_update_fName);
        et_update_houseHolder = (EditText) findViewById(R.id.et_update_houseHolder);
        et_update_occHouseHolder = (EditText) findViewById(R.id.et_update_occHouseHolder);
        et_update_age = (EditText) findViewById(R.id.et_update_age);
        et_update_nameOfMember = (EditText) findViewById(R.id.et_update_nameOfMember);

        genderGroup_update = (RadioGroup) findViewById(R.id.genderGroup_update);
        rb_male_update = (RadioButton) findViewById(R.id.rb_male_update);
        rb_female_update = (RadioButton) findViewById(R.id.rb_female_update);

        btn_update_details = (Button) findViewById(R.id.btn_update_details);

    }

    public void setOnClickHere() {
        btn_update_details.setOnClickListener(this);
        et_update_age.setOnClickListener(this);
        tb_back_iv.setOnClickListener(this);
    }

    public void toolbarBackButton(boolean img) {
        if (img == true) {
            tb_back_iv.setVisibility(View.VISIBLE);
        } else {
            tb_back_iv.setVisibility(View.GONE);
        }

    }


    public void GetPreDatabaseRecords() {
        if (bDetails != null) {
            String user_gender = detailFields.getGender_household();
            if (user_gender.equals("Male")) {
                rb_male_update.setChecked(true);
            } else if (user_gender.equals("Female")) {
                rb_female_update.setChecked(true);


            }

            et_update_fName.setText(detailFields.getFaliya_name());
            et_update_houseHolder.setText(detailFields.getHead_household());
            et_update_occHouseHolder.setText(detailFields.getOccupation_household());
            et_update_age.setText(detailFields.getAge_household());
            et_update_nameOfMember.setText(detailFields.getNames_familymembers());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_back_iv:
                finish();
                break;
            case R.id.btn_update_details:

                int selectGenderId = genderGroup_update.getCheckedRadioButtonId();
                if (selectGenderId == R.id.rb_male_update) {
                    update_gender_txt = "Male";

                } else if (selectGenderId == R.id.rb_female_update) {
                    update_gender_txt = "Female";
                }

                if (isValid()) {
                    String fname = et_update_fName.getText().toString();
                    String hhname = et_update_houseHolder.getText().toString();
                    String ohh = et_update_occHouseHolder.getText().toString();
                    String age = et_update_age.getText().toString();
                    String gender = update_gender_txt;
                    String member = et_update_nameOfMember.getText().toString();

                    detail_id = detailFields.getM_id();
                    UpdateRecordQuery = "UPDATE " + Constants.TABLE_NAME + " SET " +
                            Constants.KEY_Name1 + "='" + fname + "', " + Constants.KEY_Name2 +
                            "='" + hhname + "', " + Constants.KEY_Name3 + "='" + ohh + "',"
                            + Constants.KEY_Name4 + "='" + age + "'," + Constants.KEY_Name5 + "='"
                            + gender + "'," + Constants.KEY_Name6 + "='" + member + "' WHERE " + Constants.KEY_ID + "=" + detail_id + ";";
                    SQLITEDATABASE.execSQL(UpdateRecordQuery);
                    Toast.makeText(EditDetailsActivity.this, "Record Updated Successfully!", Toast.LENGTH_LONG).show();
                    Intent iBackToMain = new Intent();
                    setResult(Activity.RESULT_OK, iBackToMain);
                    finish();
                }


                break;
            case R.id.et_update_age:
                Toolbar toolbar;
                TextView tb_txt_tv;
                final Dialog d = new Dialog(EditDetailsActivity.this, R.style.AppTheme);
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
                np.setValue(Integer.parseInt(et_update_age.getText().toString()));
                np.setWrapSelectorWheel(false);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        et_update_age.setText(String.valueOf(np.getValue()));
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


        String fname = et_update_fName.getText().toString();
        String hh = et_update_houseHolder.getText().toString();
        String ohh = et_update_occHouseHolder.getText().toString();
        String age = et_update_age.getText().toString();
        String member = et_update_nameOfMember.getText().toString();


        if (fname.trim().isEmpty()) {
            et_update_fName.setError(getResources().getString(R.string.m_f_name));
            valid = false;
        } else {
            et_update_fName.setError(null);
        }
        if (hh.trim().isEmpty()) {
            et_update_houseHolder.setError(getResources().getString(R.string.m_hoh));
            valid = false;
        } else {
            et_update_houseHolder.setError(null);
        }
        if (ohh.trim().isEmpty()) {
            et_update_occHouseHolder.setError(getResources().getString(R.string.m_ohh));
            valid = false;
        } else {
            et_update_occHouseHolder.setError(null);
        }
        if (age.trim().isEmpty()) {
            et_update_age.setError(getResources().getString(R.string.m_age));
            valid = false;
        } else {
            et_update_age.setError(null);
        }
        if (member.isEmpty()) {
            et_update_nameOfMember.setError(getResources().getString(R.string.m_nofm));
            valid = false;
        } else {
            et_update_nameOfMember.setError(null);
        }
        return valid;
    }

}
