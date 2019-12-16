package com.example.deanaagirl.NursingDiary.TeacherActivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.deanaagirl.NursingDiary.Adapter.HelperSubjectListAdapter;
import com.example.deanaagirl.NursingDiary.Model.HelperSubject;
import com.example.deanaagirl.NursingDiary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
//public class TeacherSubjectGroupFragment extends Fragment {
//
//    private ListView lvHelperSubject;
//    private HelperSubjectListAdapter adapter;
//    private List<HelperSubject> mHelperSubjectList;
//
//    public TeacherSubjectGroupFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        ViewGroup container2;
//        View v = inflater.inflate(R.layout.fragment_main, container, false);
//
//        lvHelperSubject = (ListView)v.findViewById(R.id.listview_helper_subj);
//
//        mHelperSubjectList = new ArrayList();
//        mHelperSubjectList.add(new HelperSubject(1,"ปฏิบัติการพยาบาลฉุกเฉินและวิกฤต"));
//
//        adapter = new HelperSubjectListAdapter(getContext(),mHelperSubjectList);
//        lvHelperSubject.setAdapter(adapter);
//
//        lvHelperSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0 ){
//                    Intent intent = new Intent(view.getContext(), HelperListActivity.class);
//                    startActivityForResult(intent, 0);
//                }
//            }
//        });
//
//        // Inflate the layout for this fragment
//        return v;
//    }
//
//}