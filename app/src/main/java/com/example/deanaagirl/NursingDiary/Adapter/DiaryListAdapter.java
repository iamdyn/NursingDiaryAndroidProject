package com.example.deanaagirl.NursingDiary.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deanaagirl.NursingDiary.Connect.JSONObtained;
import com.example.deanaagirl.NursingDiary.LoginActivity;
import com.example.deanaagirl.NursingDiary.Model.Diary;
import com.example.deanaagirl.NursingDiary.Model.StudentId;
import com.example.deanaagirl.NursingDiary.Model.User;
import com.example.deanaagirl.NursingDiary.R;
import com.example.deanaagirl.NursingDiary.StudentActivity.StudenMainActivity;
import com.example.deanaagirl.NursingDiary.TeacherActivity.TeacherMainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;

/**
 * Created by DeanaAgirl on 3/7/2018.
 */

public class DiaryListAdapter extends BaseAdapter {

    Diary diary = new Diary();
    List<Diary> datalist = new ArrayList<>();
    Diary studentData = new Diary() ;

    Gson gson = new GsonBuilder().create();
    String jsonStudent;

    private Context mDiaryContext;
    private List<Diary> mDiaryList;

    public DiaryListAdapter(Context mDiaryContext, List<Diary> mDiaryList) {
        this.mDiaryContext = mDiaryContext;
        this.mDiaryList = mDiaryList;
    }

    @Override
    public int getCount() {
        return mDiaryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDiaryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mDiaryContext, R.layout.item_diary_list,null);
        TextView tvDate = v.findViewById(R.id.textview_list_date);
        TextView tvHead = v.findViewById(R.id.textview_list_header);
        TextView tvStatus = v.findViewById(R.id.textView_status);

        tvDate.setText("วันที่ขึ้นวอร์ด  "+ mDiaryList.get(position).getDateward());
        tvHead.setText(mDiaryList.get(position).getDiarytitle());
        tvStatus.setText(String.valueOf(mDiaryList.get(position).getStatusId()));
            if (mDiaryList.get(position).getStatusId() == 200){
                tvStatus.setTextColor(Color.parseColor("#FF0000"));
                tvStatus.setText("");
            }else if(mDiaryList.get(position).getStatusId() == 100) {
                tvStatus.setTextColor(Color.parseColor("#009900"));
                tvStatus.setText("ส่งแล้ว");
            }

        //Save product id to tag
        v.setTag(mDiaryList.get(position).getClass());
        return v;
    }
}
