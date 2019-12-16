package com.example.deanaagirl.NursingDiary.TeacherActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deanaagirl.NursingDiary.Adapter.DateListAdapter;
import com.example.deanaagirl.NursingDiary.Connect.JSONObtained;
import com.example.deanaagirl.NursingDiary.Model.Date;
import com.example.deanaagirl.NursingDiary.Model.Diary;
import com.example.deanaagirl.NursingDiary.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;

public class DateListActivity extends AppCompatActivity {

    private ListView lvDate;
    private DateListAdapter adapter;

    Diary datatest = new Diary();
    List<Diary> datalist = new ArrayList<>();
    Diary teachertData = new Diary() ;
    Map<String, List<Diary>> groupBydate = new HashMap<>();

    String jsonTeacher;
    Gson gson = new GsonBuilder().create();
    SharedPreferences LocalStorage;

    int subjectId;

    ArrayList<Diary> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_list);

        LocalStorage = getSharedPreferences("DATAUSER",0);
        subjectId = getIntent().getExtras().getInt("subjectId");

        listview_date();

        lvDate = findViewById(R.id.listview_date_on_ward);
        lvDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(view.getContext(), StudentListActivity.class);
                    int subjectid = datalist.get(position).getSubjectId();
                    String sortbyDate = String.valueOf(result.get(position));
                    intent.putExtra("subjectId",subjectid);
                    intent.putExtra("date",sortbyDate);
                    startActivity(intent);
            }
        });
    }

    private void listview_date() {

        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "/api/Diary/DiaryTeacherList");

        teachertData.setTeacherId(LocalStorage.getString("id","0"));
        teachertData.setSubjectId(subjectId);
        jsonTeacher = gson.toJson(teachertData);
        final JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(jsonTeacher).getAsJsonObject();

        JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback()  {
            @Override
            public void onFailure(okhttp3.Call call, final IOException e) {

                runOnUiThread(new Runnable() {
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
                        datatest = gson.fromJson(String.valueOf(jo.getJSONObject(i)),Diary.class);
                        datalist.add(datatest);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getApplicationContext(),jsonTeacher,Toast.LENGTH_LONG).show();
                        try {
                            for(Diary p : datalist){
                                if(!groupBydate.containsKey(p.getDateward())){
                                    groupBydate.put(p.getDateward(),new ArrayList<Diary>());
                                }
                                groupBydate.get(p.getDateward()).add(p);
                            }
                            result = new ArrayList(groupBydate.keySet());
                            String s = gson.toJson(datalist);
                            adapter = new DateListAdapter(getApplicationContext(),result);
                            lvDate.setAdapter(adapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }
}
