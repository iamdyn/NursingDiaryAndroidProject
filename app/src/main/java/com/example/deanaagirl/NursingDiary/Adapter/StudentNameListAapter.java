package com.example.deanaagirl.NursingDiary.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deanaagirl.NursingDiary.Model.Diary;
import com.example.deanaagirl.NursingDiary.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DeanaAgirl on 3/10/2018.
 */

public class StudentNameListAapter extends BaseAdapter {

    private Context mNameStContext;
    private List<Diary> mNameSTList;

    public StudentNameListAapter(Context mNameStContext, List<Diary> mNameSTList) {

        this.mNameStContext = mNameStContext;
        this.mNameSTList = mNameSTList;
    }

    @Override
    public int getCount() {
        return mNameSTList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNameSTList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mNameStContext, R.layout.item_name_st_list,null);
        TextView tvNameSt =  v.findViewById(R.id.textview_name_student_send);
//        TextView tvTstatus = v.findViewById(R.id.textView_Tstatus);

        tvNameSt.setText(mNameSTList.get(position).getStudentName());
//        tvTstatus.setText(String.valueOf(mNameSTList.get(position).getStatusId()));
//
//        if (mNameSTList.get(position).getStatusId() == 200){
//            tvTstatus.setTextColor(Color.parseColor("#FF0000"));
//            tvTstatus.setText("บันทึก");
//        }else if(mNameSTList.get(position).getStatusId() == 100) {
//            tvTstatus.setTextColor(Color.parseColor("#009900"));
//            tvTstatus.setText("ส่งแล้ว");
//        }

        //Save product id to tag
//        v.setTag(mNameSTList.get(position).getClass());

        return v;
    }
}
