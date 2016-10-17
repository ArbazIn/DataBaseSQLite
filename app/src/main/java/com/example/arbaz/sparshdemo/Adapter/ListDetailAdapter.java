package com.example.arbaz.sparshdemo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arbaz.sparshdemo.Model.DetailFields;
import com.example.arbaz.sparshdemo.R;
import com.example.arbaz.sparshdemo.Screens.EditDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arbaz on 14/10/16.
 */

public class ListDetailAdapter extends BaseAdapter {

    Context context;
    List<DetailFields> detailFieldses = new ArrayList<DetailFields>();
    DetailFields detailField;


    public ListDetailAdapter(List<DetailFields> detailFieldses, Context context) {
        this.detailFieldses = detailFieldses;
        this.context = context;


    }


    @Override
    public int getCount() {
        return detailFieldses.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        LayoutInflater layoutInflater;
        DetailFields detailFieldList;
        detailFieldList = detailFieldses.get(position);


        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_details_row, null);
            holder = new Holder();

            holder.ll_details = (LinearLayout) convertView.findViewById(R.id.ll_details);
            holder.row_tv_fname = (TextView) convertView.findViewById(R.id.tv_fname);
            holder.row_tv_hoh = (TextView) convertView.findViewById(R.id.tv_hoh);
            holder.row_tv_oohh = (TextView) convertView.findViewById(R.id.tv_oohh);
            holder.row_tv_age = (TextView) convertView.findViewById(R.id.tv_age);
            holder.row_tv_gender = (TextView) convertView.findViewById(R.id.tv_gender);
            holder.row_tv_nofm = (TextView) convertView.findViewById(R.id.tv_nofm);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.ll_details.setTag(detailFieldList);
        holder.row_tv_fname.setText(detailFieldList.getFaliya_name());
        holder.row_tv_hoh.setText(detailFieldList.getHead_household());
        holder.row_tv_oohh.setText(detailFieldList.getOccupation_household());
        holder.row_tv_age.setText(detailFieldList.getAge_household());
        holder.row_tv_gender.setText(detailFieldList.getGender_household());
        holder.row_tv_nofm.setText(detailFieldList.getNames_familymembers());


        holder.ll_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFields detailFields1;
                detailFields1 = (DetailFields) v.getTag();
                Intent iEdit = new Intent(context, EditDetailsActivity.class);
                iEdit.putExtra("EditDetails", detailFields1);
                ((Activity) context).startActivityForResult(iEdit, 1);

            }
        });
        return convertView;
    }

    public class Holder {
        LinearLayout ll_details;
        TextView row_tv_fname;
        TextView row_tv_hoh;
        TextView row_tv_oohh;
        TextView row_tv_age;
        TextView row_tv_gender;
        TextView row_tv_nofm;
    }

    public DetailFields getDetailField(int position) {
        return detailFieldses.get(position);
    }


}
