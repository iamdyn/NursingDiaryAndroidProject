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
 * Created by DeanaAgirl on 3/7/2018.
 */
    public class SubjectListAdapter extends BaseAdapter{

    private Context mSubjContext;
    private List<Subjects> mSubjecList;

    public SubjectListAdapter(Context mSubjContext, List<Subjects> mSubjecList) {
        this.mSubjContext = mSubjContext;
        this.mSubjecList = mSubjecList;
    }

    @Override
    public int getCount() {
        return mSubjecList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSubjecList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mSubjContext, R.layout.item_subject_list,null);

        TextView tvSubj = v.findViewById(R.id.textview_name_subj);
        TextView tvTGroup = v.findViewById(R.id.textview_teacher_group);

        tvSubj.setText(mSubjecList.get(position).getSubjectName());
        tvTGroup.setText(mSubjecList.get(position).getTeacherName());

        //Save product id to tag
        v.setTag(mSubjecList.get(position).getId());
        return v;
    }
}

