package com.example.deanaagirl.NursingDiary.TeacherActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.deanaagirl.NursingDiary.Adapter.HelperListAdapter;
import com.example.deanaagirl.NursingDiary.Model.Helper;
import com.example.deanaagirl.NursingDiary.R;

import java.util.ArrayList;
import java.util.List;

//public class HelperListActivity extends AppCompatActivity {
//
//
//    private ListView lvHelper;
//    private HelperListAdapter adapter;
//    private List<Helper> mHelperList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_helper_list);
//
//        lvHelper = (ListView)findViewById(R.id.listview_name_helper);
//
//        mHelperList = new ArrayList<>();
//        //Add sample data for list
//        //We can get data from DB, webservice here
//
//        mHelperList.add(new Helper(1,"สมชาย เข็มหมุด"));
//        mHelperList.add(new Helper(2, "ขยัน หมั่นเพียร"));
//
//        adapter = new HelperListAdapter(getApplicationContext(),mHelperList);
//        lvHelper.setAdapter(adapter);
//
//        lvHelper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            //คลิก listview
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0 ){
//                    Intent intent = new Intent(view.getContext(), StudentNameListActivity.class);
//                    startActivityForResult(intent, 0);
//                }
//            }
//        });
//    }
//}
