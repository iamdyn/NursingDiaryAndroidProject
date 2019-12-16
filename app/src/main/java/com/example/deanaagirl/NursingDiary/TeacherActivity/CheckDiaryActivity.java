package com.example.deanaagirl.NursingDiary.TeacherActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deanaagirl.NursingDiary.Adapter.CommentAdapter;
import com.example.deanaagirl.NursingDiary.Connect.JSONObtained;
import com.example.deanaagirl.NursingDiary.Model.Comment;
import com.example.deanaagirl.NursingDiary.Model.Diary;
import com.example.deanaagirl.NursingDiary.Model.HashtagDetail;
import com.example.deanaagirl.NursingDiary.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

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

public class CheckDiaryActivity extends AppCompatActivity {

    TextView sh_date, sh_title, sh_content,Tsh_hashtag0, Tsh_hashtag1, Tsh_hashtag2;
    Diary diary = new Diary();
    Diary teachertData = new Diary();
    Comment teachertData2 = new Comment();
    Comment comment = new Comment();
    JsonObject objectFromString;

    Gson gson = new GsonBuilder().create();
    String jsonTeacher;
    String jsonTeacher2;
    String JsonDiary;

    EditText edtComment;
    ImageButton btnComment;
    TextView tvComment;

    CommentAdapter adapter;
    ListView lvComment;
    List<Comment> datalist = new ArrayList<>();
    Comment datatest = new Comment();

    int diaryId;

    String url;
    ImageView view;
    SharedPreferences LocalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_diary);

        LocalStorage = getSharedPreferences("DATAUSER", 0);

        sh_date = findViewById(R.id.textView_Tsh_date);
        sh_title = findViewById(R.id.textView_Tsh_title);
        sh_content = findViewById(R.id.textView_Tsh_content);
        Tsh_hashtag0 = findViewById(R.id.textView_Tsh_hashtag0);
        Tsh_hashtag1 = findViewById(R.id.textView_Tsh_hashtag1);
        Tsh_hashtag2 = findViewById(R.id.textView_Tsh_hashtag2);
        tvComment = findViewById(R.id.textView_comment);
        edtComment = findViewById(R.id.editText_comment);
        lvComment = findViewById(R.id.listview_comment2);
        view = findViewById(R.id.imageView_check);

        check_diary();

        commentActivity();
        show_comment();

    }

    private void image(String url) {
        Picasso.with(this).load(url)
//                .error(R.mipmap.ic_launcher)
                .into(view, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void commentActivity() {
        btnComment = findViewById(R.id.button_comment);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty()) {
                    comment_diary();
                    show_comment();
                }
            }
        });
    }

    private boolean checkEmpty() {
        if (edtComment.getText().length() == 0) {
            return false;
        }
        return true;
        }

        private void check_diary () {
            //   Toast.makeText(getApplicationContext(), DiaryId, Toast.LENGTH_SHORT).show();

            HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/Diary/DiaryDetail");
            diaryId = getIntent().getExtras().getInt("diaryId");
            teachertData.setDiaryId(diaryId);
            jsonTeacher = gson.toJson(teachertData);

            final JsonParser jsonParser = new JsonParser();
            JsonObject objectFromString = jsonParser.parse(jsonTeacher).getAsJsonObject();

            JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, final IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {

                    jsonTeacher = response.body().string();
                    try {
                        JSONObject jo = new JSONObject(jsonTeacher);
                        diary = gson.fromJson(jsonTeacher, Diary.class);


                    } catch (Exception e) {

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                JSONObject jObj = new JSONObject(jsonTeacher);
                                String date = diary.getDateWardToString();
                                String title = diary.getDiarytitle();
                                String content = diary.getDiarycontent();
                                ArrayList<HashtagDetail> hashtags = diary.getDiaryHashtagDetails();
                                Object diaryPicture = diary.getDiaryPictureDetials().getPicName();

                                url = String.valueOf(diaryPicture);
                                sh_date.setText(date);
                                sh_title.setText(title);
                                sh_content.setText(content);
                                Tsh_hashtag0.setText(hashtags.get(0).getHashTagName().toString());
                                Tsh_hashtag1.setText(hashtags.get(1).getHashTagName().toString());
                                Tsh_hashtag2.setText(hashtags.get(2).getHashTagName().toString());
                                image(url);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }

        private void comment_diary () {

            HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/Comment/CreateComment");

            JSONObject jsonObject = new JSONObject();

            String commentText = edtComment.getText().toString();
            String teacherId = LocalStorage.getString("id", "0");
            diaryId = getIntent().getExtras().getInt("diaryId");
            try {

                comment.setCommentContent(commentText);
                comment.setDiaryId(diaryId);
                comment.setTeacherId(teacherId);

                JsonDiary = gson.toJson(comment);
                JsonParser jsonParser = new JsonParser();
                objectFromString = jsonParser.parse(JsonDiary).getAsJsonObject();

                jsonObject.put("Comment", commentText);
                jsonObject.put("diaryId", diaryId);
                jsonObject.put("teacherId", teacherId);

                edtComment.setText("");
                show_comment();

            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    final String json = response.body().string();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }


        private void show_comment () {

            HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/Comment/CommentDetail");
            teachertData2.setDiaryId(diaryId);
            jsonTeacher2 = gson.toJson(teachertData2);
            final JsonParser jsonParser = new JsonParser();
            JsonObject objectFromString = jsonParser.parse(jsonTeacher2).getAsJsonObject();

            JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, final IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {

                    jsonTeacher2 = response.body().string();
                    try {
                        JSONArray jo = new JSONArray(jsonTeacher2);
                        for (int i = 0; i < jo.length(); i++) {
                            datatest = gson.fromJson(String.valueOf(jo.getJSONObject(i)), Comment.class);
                            datalist.add(datatest);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(getApplicationContext(), jsonTeacher2, Toast.LENGTH_LONG).show();
                            try {
                                String s = gson.toJson(datalist);
                                if (datalist != null) {
                                    datalist.get(datalist.size() -1);
                                    adapter = new CommentAdapter(getApplicationContext(), datalist);
                                    lvComment.setAdapter(adapter);
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

