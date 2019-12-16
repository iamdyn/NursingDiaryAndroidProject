package com.example.deanaagirl.NursingDiary.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deanaagirl.NursingDiary.Model.Subjects;
import com.example.deanaagirl.NursingDiary.R;

import java.util.List;

/**
 * Created by DeanaAgirl on 3/11/2018.
 */

public class TSubjectListAdapter extends BaseAdapter {

    private Context mTSubjContext;
    private List<Subjects> mTSubjecList;

    public TSubjectListAdapter(Context mTSubjContext, List<Subjects> mTSubjecList) {
        this.mTSubjContext = mTSubjContext;
        this.mTSubjecList = mTSubjecList;
    }

    @Override
    public int getCount() {
        return mTSubjecList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTSubjecList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mTSubjContext, R.layout.item_teacher_subject_list,null);

        TextView tvTSubj = (TextView) v.findViewById(R.id.textview_teacher_name_subj);

        tvTSubj.setText(mTSubjecList.get(position).getSubjectName());

        //Save product id to tag
        v.setTag(mTSubjecList.get(position).getId());
        return v;
    }
}