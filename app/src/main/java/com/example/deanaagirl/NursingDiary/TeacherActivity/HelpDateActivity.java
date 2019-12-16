package com.example.deanaagirl.NursingDiary.TeacherActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.deanaagirl.NursingDiary.Adapter.HelpDateListAdapter;
import com.example.deanaagirl.NursingDiary.Model.HelpDate;
import com.example.deanaagirl.NursingDiary.R;

import java.util.ArrayList;
import java.util.List;

public class HelpDateActivity extends AppCompatActivity {

    private ListView lvHelpDate;
    private HelpDateListAdapter adapter;
    private List<HelpDate> mHelpDateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_date);


        lvHelpDate = (ListView)findViewById(R.id.listview_help_date_on_ward);

        mHelpDateList = new ArrayList<>();
        mHelpDateList.add(new HelpDate(1,"2/02/2018"));
        mHelpDateList.add(new HelpDate(2,"5/02/2018"));

        adapter = new HelpDateListAdapter(getApplicationContext(),mHelpDateList);
        lvHelpDate.setAdapter(adapter);

        lvHelpDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
    }
}
