package com.example.deanaagirl.NursingDiary.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deanaagirl.NursingDiary.Model.HelperStName;
import com.example.deanaagirl.NursingDiary.R;

import java.util.List;

/**
 * Created by DeanaAgirl on 3/12/2018.
 */

public class HelperStNameListAdapter extends BaseAdapter {

    private Context mHelperStNameContext;
    private List<HelperStName> mHelperStNameList;

    public HelperStNameListAdapter(Context mDateHelperContext, List<HelperStName> mHelperStNameList) {
        this.mHelperStNameContext = mDateHelperContext;
        this.mHelperStNameList = mHelperStNameList;
    }

    @Override
    public int getCount() {
        return mHelperStNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHelperStNameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(mHelperStNameContext, R.layout.item_helper_st_name_list,null);
            TextView tvStName = (TextView) v.findViewById(R.id.textview_list_helper_st_name);
            tvStName.setText(mHelperStNameList.get(position).getHelpStName());


            //Save product id to tag
            v.setTag(mHelperStNameList.get(position).getId());

            return v;
    }
}
