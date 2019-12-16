package com.example.deanaagirl.NursingDiary.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deanaagirl.NursingDiary.Model.HelperSubject;
import com.example.deanaagirl.NursingDiary.R;

import java.util.List;

/**
 * Created by DeanaAgirl on 3/13/2018.
 */

public class HelperSubjectListAdapter extends BaseAdapter{

    private Context mHelperSubjContext;
    private List<HelperSubject> mHelperSubjecList;

    public HelperSubjectListAdapter(Context mHelperSubjContext, List<HelperSubject> mHelperSubjecList) {
        this.mHelperSubjContext = mHelperSubjContext;
        this.mHelperSubjecList = mHelperSubjecList;
    }

    @Override
    public int getCount() {
        return mHelperSubjecList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHelperSubjecList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mHelperSubjContext, R.layout.item_helper_subj,null);

        TextView tvHelperSubject = (TextView) v.findViewById(R.id.textview_helper_name_subj);

        tvHelperSubject.setText(mHelperSubjecList.get(position).getHelperSubject());

        //Save product id to tag
        v.setTag(mHelperSubjecList.get(position).getId());
        return v;
    }
}
