package com.example.deanaagirl.NursingDiary.TeacherActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.deanaagirl.NursingDiary.Adapter.HelperStNameListAdapter;
import com.example.deanaagirl.NursingDiary.Model.HelperStName;
import com.example.deanaagirl.NursingDiary.R;

import java.util.ArrayList;
import java.util.List;

public class HelperStNameActivity extends AppCompatActivity {

    private ListView lvDateHelper;
    private HelperStNameListAdapter adapter;
    private List<HelperStName> mHelperStNameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_st_name);


            lvDateHelper = (ListView) findViewById(R.id.listview_helper_st_name);

            mHelperStNameList = new ArrayList<>();
            mHelperStNameList.add(new HelperStName(1,"ดวงใจ แก้วดี"));

            adapter = new HelperStNameListAdapter(getApplicationContext(), mHelperStNameList);
            lvDateHelper.setAdapter(adapter);

            lvDateHelper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }
    }
