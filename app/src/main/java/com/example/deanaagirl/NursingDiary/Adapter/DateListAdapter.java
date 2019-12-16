package com.example.deanaagirl.NursingDiary.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deanaagirl.NursingDiary.R;

import java.util.ArrayList;

/**
 * Created by DeanaAgirl on 3/10/2018.
 */

public class DateListAdapter extends BaseAdapter{

    private Context mDateContext;
    private ArrayList mDateList;

    public DateListAdapter(Context mDateContext, ArrayList mDateList) {

        this.mDateContext = mDateContext;
        this.mDateList = mDateList;

    }

    @Override
    public int getCount() {
        return mDateList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mDateContext, R.layout.item_date_list,null);
        TextView tvDate = v.findViewById(R.id.textview_list_date_on_ward);
        tvDate.setText(mDateList.get(position).toString());

        //Save product id to tag
        v.setTag(mDateList.get(position));

        return v;
    }
}
