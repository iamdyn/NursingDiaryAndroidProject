package com.example.deanaagirl.NursingDiary.StudentActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deanaagirl.NursingDiary.Adapter.DiaryListAdapter;
import com.example.deanaagirl.NursingDiary.Connect.JSONObtained;
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
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;

public class DiaryListActivity extends AppCompatActivity {

    ListView lvDiary;
    DiaryListAdapter adapter;

    Diary diary = new Diary();
    List<Diary> datalist = new ArrayList<>();
    Diary studentData = new Diary() ;

    int subjectId;

    Gson gson = new GsonBuilder().create();
    String jsonStudent;
    SharedPreferences LocalStorage,lcSubjectId;

    SwipeRefreshLayout swipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_diary);

//        progressBar = (findViewById(R.id.progressBar_list_diary));
//        swipe = findViewById(R.id.swipeLayout_list_diary);
//        swipe.setOnRefreshListener(this);

        LocalStorage = getSharedPreferences("DATAUSER",0);
//        lcSubjectId = getSharedPreferences("SUBJECID",0);
        subjectId = getIntent().getExtras().getInt("subjectId");

        listview_diary();

        lvDiary = (ListView) findViewById(R.id.listview_list_diary);
        lvDiary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    int diaryid = datalist.get(i).getDiaryId();
                    int statusId = datalist.get(i).getStatusId();
                    if (statusId == 200){
                        Intent intent = new Intent(view.getContext(), UpdateActivity.class);
                        intent.putExtra("diaryId", diaryid);
                        startActivity(intent);
                    }else if (statusId == 100){
                        Intent intent = new Intent(view.getContext(), ViewDiaryActivity.class);
                        intent.putExtra("diaryId", diaryid);
                        startActivity(intent);
                    }
            }
        });
    }
    //button add diary
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_diary) {
            Intent intent = new Intent(this,WriteDiaryActivity.class);
            intent.putExtra("subjectId",subjectId);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void listview_diary() {

        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/Diary/DiaryStudentList");

//        subjectId = getIntent().getExtras().getInt("subjectId");
        studentData.setStudentId(LocalStorage.getString("id","0"));
        studentData.setSubjectId(subjectId);
        jsonStudent = gson.toJson(studentData);
        final JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(jsonStudent).getAsJsonObject();

        JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback()  {
            @Override
            public void onFailure(okhttp3.Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                jsonStudent = response.body().string();
                try {
                    JSONArray jo = new JSONArray(jsonStudent);
                    for(int i = 0 ;i<jo.length();i++){
                       diary = gson.fromJson(String.valueOf(jo.getJSONObject(i)),Diary.class);
                       datalist.add(diary);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getApplicationContext(),jsonStudent,Toast.LENGTH_LONG).show();
                        try {
                            String s = gson.toJson(datalist);
                            adapter = new DiaryListAdapter(getApplicationContext(),datalist);
                            if (s != null) {
                                lvDiary.setAdapter(adapter);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"ยังไม่มีไดอารี่",Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

//    @Override
//    public void onRefresh() {
//        swipe.setRefreshing(false);
//
//    }
}