package com.example.deanaagirl.NursingDiary.TeacherActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deanaagirl.NursingDiary.Adapter.StudentNameListAapter;
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

import static com.androidquery.util.AQUtility.getContext;

//public class StudentNameListActivity extends AppCompatActivity {
//
//    private ListView lvNameSt;
//    private StudentNameListAapter adapter;
//
//    Diary datatest = new Diary();
//    List<Diary> datalist = new ArrayList<>();
//    Diary teachertData = new Diary() ;
//
//    int subjectId;
//
//    String jsonTeacher;
//    Gson gson = new GsonBuilder().create();
//    SharedPreferences LocalStorage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_name_student_list);
//
//        LocalStorage = getSharedPreferences("DATAUSER",0);
//        subjectId = getIntent().getExtras().getInt("subjectId");
////        diaryId = getIntent().getExtras().getInt("diaryId");
////        studentName = getIntent().getExtras().getString("studentName");
//
//        listview_student_name();
//
//        lvNameSt = findViewById(R.id.listview_name_student_send);
//        lvNameSt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(view.getContext(), CheckDiaryActivity.class);
//                    int diaryid = datalist.get(position).getDiaryId();
//                    intent.putExtra("diaryId",diaryid);
//                    startActivity(intent);
//            }
//        });
//    }
//
//    private void listview_student_name() {
//
//        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "/api/Diary/DiaryTeacherList");
//
//        teachertData.setTeacherId(LocalStorage.getString("id","0"));
//        teachertData.setSubjectId(subjectId);
//        jsonTeacher = gson.toJson(teachertData);
//        final JsonParser jsonParser = new JsonParser();
//        JsonObject objectFromString = jsonParser.parse(jsonTeacher).getAsJsonObject();
//
//        JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback()  {
//            @Override
//            public void onFailure(okhttp3.Call call, final IOException e) {
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
////                        Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//
//                jsonTeacher = response.body().string();
//                try {
//                    JSONArray jo = new JSONArray(jsonTeacher);
//                    for(int i = 0 ;i<jo.length();i++){
//                        datatest = gson.fromJson(String.valueOf(jo.getJSONObject(i)),Diary.class);
//                        datalist.add(datatest);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
////                        Toast.makeText(getApplicationContext(),jsonTeacher,Toast.LENGTH_LONG).show();
//                        try {
//                            String s = gson.toJson(datalist);
//                            adapter = new StudentNameListAapter(getApplicationContext(),datalist);
//                            lvNameSt.setAdapter(adapter);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        });
//    }
//
//
//}
