package com.example.deanaagirl.NursingDiary.StudentActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deanaagirl.NursingDiary.Adapter.SubjectListAdapter;
import com.example.deanaagirl.NursingDiary.Connect.JSONObtained;
import com.example.deanaagirl.NursingDiary.Model.Subjects;
import com.example.deanaagirl.NursingDiary.Model.User;
import com.example.deanaagirl.NursingDiary.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment{

    private ListView lvSubject;
    private SubjectListAdapter adapter;
    private List<Subjects> mSubjectList;

    Subjects datatest = new Subjects();
    List<Subjects> datalist = new ArrayList<>();
    Subjects studentData = new Subjects() ;
    Subjects subjects = new Subjects();

    String jsonStudent;
    User user = new User();
    Gson gson = new GsonBuilder().create();
    SharedPreferences LocalStorage;

    String teacherId, qrcode;
    JsonObject objectFromString;
    String JsonSubjects;

    public MainFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Confirm this fragment has menu items.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        teacherId = getActivity().getIntent().getExtras().getString("teacherId");
        qrcode = getActivity().getIntent().getExtras().getString("QR Code");
        LocalStorage = getActivity().getSharedPreferences("DATAUSER",0);
        String id = LocalStorage.getString("id","0");
            if (teacherId == null && qrcode == null){
                listview_subject();
            }else {
                add_subject();
                listview_subject();
            }


        // คลิก listview
        lvSubject = (ListView)v.findViewById(R.id.listview_my_subj);
        lvSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), DiaryListActivity.class);
                int subjectid = datalist.get(position).getSubjectId();
                intent.putExtra("subjectId",subjectid);
                startActivityForResult(intent, 0);
            }
        });


        return v;

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the fragment menu items.
        inflater.inflate(R.menu.mymenu, menu);

    }

    private void add_subject() {
        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/Subject/AddSubject");
        JSONObject jsonObject = new JSONObject();

        String studentId = LocalStorage.getString("id","0");

            subjects.setStudentId(studentId);
            subjects.setQrcode(qrcode);
            subjects.setTeacherId(teacherId);

            JsonSubjects = gson.toJson(subjects);
            JsonParser jsonParser = new JsonParser();
            objectFromString = jsonParser.parse(JsonSubjects).getAsJsonObject();

        JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String json = response.body().string();
                final Boolean reback = Boolean.valueOf(json);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (reback == false ){
                            Toast.makeText(getContext(),"คุณมีวิชานี้แล้ว",Toast.LENGTH_SHORT).show();
//                            Toast.makeText(getContext(), json, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void listview_subject() {
        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "/api/Subject/SubjectStudentList");

        studentData.setStudentId(LocalStorage.getString("id","0"));
        jsonStudent = gson.toJson(studentData);
        final JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(jsonStudent).getAsJsonObject();

        JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback()  {
            @Override
            public void onFailure(okhttp3.Call call, final IOException e) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                jsonStudent = response.body().string();
                try {
                    JSONArray jo = new JSONArray(jsonStudent);
                    for(int i = 0 ;i<jo.length();i++){
                        datatest = gson.fromJson(String.valueOf(jo.getJSONObject(i)),Subjects.class);
                        datalist.add(datatest);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getContext(),jsonStudent,Toast.LENGTH_LONG).show();
                        try {
                            String s = gson.toJson(datalist);
                            adapter = new SubjectListAdapter(getContext(),datalist);
                            lvSubject.setAdapter(adapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
