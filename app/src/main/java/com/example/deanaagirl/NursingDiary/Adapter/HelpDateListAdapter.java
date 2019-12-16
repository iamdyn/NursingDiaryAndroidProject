package com.example.deanaagirl.NursingDiary.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deanaagirl.NursingDiary.Model.HelpDate;
import com.example.deanaagirl.NursingDiary.R;

import java.util.List;

/**
 * Created by DeanaAgirl on 3/12/2018.
 */

public class HelpDateListAdapter extends BaseAdapter {

    private Context mHelpDateContext;
    private List<HelpDate> mHelpDateList;

    public HelpDateListAdapter(Context mHelpDateContext, List<HelpDate> mHelpDateList) {
        this.mHelpDateContext = mHelpDateContext;
        this.mHelpDateList = mHelpDateList;
    }

    @Override
    public int getCount() {
        return mHelpDateList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHelpDateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mHelpDateContext, R.layout.item_help_date_list,null);
        TextView tvHelpDate = (TextView) v.findViewById(R.id.textview_list_help_date_on_ward);
        tvHelpDate.setText(mHelpDateList.get(position).getHelpDate());


        //Save product id to tag
        v.setTag(mHelpDateList.get(position).getId());

        return v;
    }
}
