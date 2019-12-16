package com.example.deanaagirl.NursingDiary.TeacherActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deanaagirl.NursingDiary.Adapter.TSubjectListAdapter;
import com.example.deanaagirl.NursingDiary.Connect.JSONObtained;
import com.example.deanaagirl.NursingDiary.Model.Subjects;
import com.example.deanaagirl.NursingDiary.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;

public class TeacherMainFragment extends Fragment {

    private ListView lvTSubject;
    private TSubjectListAdapter adapter;

    Subjects datatest = new Subjects();
    List<Subjects> datalist = new ArrayList<>();
    Subjects teachertData = new Subjects() ;

    String jsonTeacher;
    Gson gson = new GsonBuilder().create();
    SharedPreferences LocalStorage;

    public TeacherMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        LocalStorage = getActivity().getSharedPreferences("DATAUSER",0);
        String id = LocalStorage.getString("id","0");

        listview_subject();

        lvTSubject = (ListView)v.findViewById(R.id.listview_my_subj);
        lvTSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(view.getContext(), DateListActivity.class);
                    int subjectid = datalist.get(position).getSubjectId();
                    intent.putExtra("subjectId",subjectid);
                    startActivityForResult(intent, 0);
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    private void listview_subject() {
        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "/api/Subject/SubjectTeacherList");

        teachertData.setTeacherId(LocalStorage.getString("id","0"));
        jsonTeacher = gson.toJson(teachertData);
        final JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(jsonTeacher).getAsJsonObject();

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

                jsonTeacher = response.body().string();
                try {
                    JSONArray jo = new JSONArray(jsonTeacher);
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
//                        Toast.makeText(getContext(),jsonTeacher,Toast.LENGTH_LONG).show();

                        try {

                            String s = gson.toJson(datalist);
                            if (s != null ){
                                adapter = new TSubjectListAdapter(getContext(), datalist);
                                lvTSubject.setAdapter(adapter);
                            }else {

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

}
