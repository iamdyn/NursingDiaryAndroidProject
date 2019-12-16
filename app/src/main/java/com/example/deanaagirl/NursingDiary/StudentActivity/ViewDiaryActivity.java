package com.example.deanaagirl.NursingDiary.StudentActivity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.deanaagirl.NursingDiary.Adapter.CommentAdapter;
import com.example.deanaagirl.NursingDiary.Connect.JSONObtained;
import com.example.deanaagirl.NursingDiary.Model.Comment;
import com.example.deanaagirl.NursingDiary.Model.Diary;
import com.example.deanaagirl.NursingDiary.Model.DiaryDetailModel;
import com.example.deanaagirl.NursingDiary.Model.HashtagDetail;
import com.example.deanaagirl.NursingDiary.Model.Pictures;
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

public class ViewDiaryActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private TextView View_sh_date, View_sh_title, View_sh_content, View_sh_hashtag0, View_sh_hashtag1, View_sh_hashtag2 , tvComment;
    private ImageView sh_image;
    private Button btnUpdate;

    DiaryDetailModel model = new DiaryDetailModel();
    Pictures picture = new Pictures();
    Comment comment = new Comment();
    Diary studentData = new Diary();
    Comment studentData2 = new Comment();

    Gson gson = new GsonBuilder().create();
    String jsonStudent;
    String jsonStudent2;

    CommentAdapter adapter;
    ListView lvComment;
    List<Comment> datalist = new ArrayList<>();
    Comment datatest = new Comment();

    String url;

    ProgressBar progressBar;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_diary);

//        progressBar = (findViewById(R.id.progressBar_view_diary));
//        swipe = findViewById(R.id.swipeLayout_view_diary);
//        swipe.setOnRefreshListener(this);

        lvComment = findViewById(R.id.list_comment);
        tvComment = findViewById(R.id.textView_CM);
        btnUpdate = findViewById(R.id.button_update);
        View_sh_date = findViewById(R.id.textView_sh_date);
        View_sh_title = findViewById(R.id.textView_sh_title);
        View_sh_content = findViewById(R.id.textView_sh_content);
        sh_image = findViewById(R.id.imageView_sh);
        View_sh_hashtag0 = findViewById(R.id.textView_sh_hashtag0);
        View_sh_hashtag1 = findViewById(R.id.textView_sh_hashtag1);
        View_sh_hashtag2 = findViewById(R.id.textView_sh_hashtag2);

        view_diary();
        view_comment();
//        Update();
    }

    private void image(String url) {
        Picasso.with(this).load(url)
//                .error(R.mipmap.ic_launcher)
                .into(sh_image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void view_diary() {

        final int diaryId = getIntent().getExtras().getInt("diaryId");

        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/Diary/DiaryDetail");
        studentData.setDiaryId(diaryId);
        jsonStudent = gson.toJson(studentData);
        final JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(jsonStudent).getAsJsonObject();

        JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback() {
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
                    JSONObject jo = new JSONObject(jsonStudent);
                    model = gson.fromJson(jsonStudent, DiaryDetailModel.class);




                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jObj = new JSONObject(jsonStudent);

                            String date = model.getDateWardToString();
                            String title = model.getDiaryTitle();
                            String content = model.getDiaryContent();
                            ArrayList<HashtagDetail> hashtags = model.getDiaryHashtagDetails();
                            Object diaryPicture = model.getDiaryPictureDetials().getPicName();
                            url = String.valueOf(diaryPicture);

                            View_sh_date.setText(date);
                            View_sh_title.setText(title);
                            View_sh_content.setText(content);
                            View_sh_hashtag0.setText(hashtags.get(0).getHashTagName().toString());
                            View_sh_hashtag1.setText(hashtags.get(1).getHashTagName().toString());
                            View_sh_hashtag2.setText(hashtags.get(2).getHashTagName().toString());

                            image(url);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
//    private void Update() {
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ViewDiaryActivity.this, UpdateActivity.class);
////                int diaryid = diary.getDiaryId();
////                intent.putExtra("diaryId",diaryid);
//                startActivity(intent);
//            }
//        });
//
//
//    }

    private void view_comment() {

        final int diaryId = getIntent().getExtras().getInt("diaryId");

        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/Comment/CommentDetail");
        studentData2.setDiaryId(diaryId);
        jsonStudent2 = gson.toJson(studentData2);
        final JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(jsonStudent2).getAsJsonObject();

        JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback() {
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

                jsonStudent2 = response.body().string();
                try {
                    JSONArray jo = new JSONArray(jsonStudent2);
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
//                        Toast.makeText(getApplicationContext(), jsonStudent2, Toast.LENGTH_LONG).show();
                        try {
                            String s = gson.toJson(datalist);
                            adapter = new CommentAdapter(getApplicationContext(), datalist);
                            lvComment.setAdapter(adapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(false);
    }
}

