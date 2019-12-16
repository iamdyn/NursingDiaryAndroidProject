package com.example.deanaagirl.NursingDiary.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deanaagirl.NursingDiary.Model.Helper;
import com.example.deanaagirl.NursingDiary.R;

import java.util.List;

/**
 * Created by DeanaAgirl on 3/10/2018.
 */

public class HelperListAdapter extends BaseAdapter {

    private Context mHelperContext;
    private List<Helper> mHelperList;

    public HelperListAdapter(Context mHelperContext, List<Helper> mHelperList) {
        this.mHelperContext = mHelperContext;
        this.mHelperList = mHelperList;
    }


    @Override
    public int getCount() {
        return mHelperList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHelperList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mHelperContext, R.layout.item_helper_list,null);
        TextView tvHelper = (TextView) v.findViewById(R.id.textview_name_helper);
        tvHelper.setText(mHelperList.get(position).getHelper());

        //Save product id to tag
        v.setTag(mHelperList.get(position).getId());

        return v;
    }
}
