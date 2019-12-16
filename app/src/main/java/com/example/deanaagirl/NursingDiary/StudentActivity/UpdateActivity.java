package com.example.deanaagirl.NursingDiary.StudentActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.example.deanaagirl.NursingDiary.BuildConfig;
import com.example.deanaagirl.NursingDiary.Connect.AppConfig;
import com.example.deanaagirl.NursingDiary.Connect.JSONObtained;
import com.example.deanaagirl.NursingDiary.Model.Diary;
import com.example.deanaagirl.NursingDiary.Model.HashTagCreate;
import com.example.deanaagirl.NursingDiary.Model.HashtagDetail;
import com.example.deanaagirl.NursingDiary.Model.DiaryHashTagModel;
import com.example.deanaagirl.NursingDiary.R;
import com.example.deanaagirl.NursingDiary.Service.ApiConfig;
import com.example.deanaagirl.NursingDiary.Service.ServerResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    Diary diary = new Diary();
    Diary studentData = new Diary();

    Gson gson = new GsonBuilder().create();
    String jsonStudent;
    String JsonDiary;
    JsonObject objectFromString;

    EditText edtTitle , edtContent ,edtHashtag1 ,edtHashtag2 , edtHashtag3;

    TextView Date;
    private TextView DisplayDate;
    private DatePickerDialog.OnDateSetListener DateSetListener;
    String dateWard ;

    Button btnUpdate,btnSend , btnEditPic;
    int subjectId;
    SharedPreferences LocalStorage,lcSubjectId;

    int diaryId;

    String url;
    ImageView viewPic;


    //รูป
//    ImageView imageView;
//    Button pickImage, upload;
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PICK_PHOTO = 2;
    private Uri mMediaUri;
    private static final int CAMERA_PIC_REQUEST = 1111;

    private static final String TAG = WriteDiaryActivity.class.getSimpleName();

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri;

    private String mediaPath;

    Uri selectedImage;

    private Button btnCapturePicture;

    private String mImageFileLocation = "";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
    ProgressDialog pDialog;
    String postPath;

    File file ;
    String pic;

    SwipeRefreshLayout swipeRefreshLayout;
    Handler handle;
    Runnable runable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        LocalStorage = getSharedPreferences("DATAUSER", 0);
        diaryId = getIntent().getExtras().getInt("diaryId");

        edtTitle = findViewById(R.id.editText_update_title);
        edtContent = findViewById(R.id.editText_update_content);
        Date = findViewById(R.id.textView_update_date);
        edtHashtag1 = findViewById(R.id.editText_update_hashtag1);
        edtHashtag2 = findViewById(R.id.editText_update_hashtag2);
        edtHashtag3 = findViewById(R.id.editText_update_hashtag3);
        viewPic = findViewById(R.id.imageView_update);
        btnUpdate = findViewById(R.id.button_update);
        btnSend = findViewById(R.id.button_send2);
        btnEditPic = findViewById(R.id.button_edit_pic);

        btnEditPic.setOnClickListener(this);
        edtTitle.addTextChangedListener(saveActivity);
        Date.addTextChangedListener(saveActivity);
        edtContent.addTextChangedListener(saveActivity);
        edtHashtag1.addTextChangedListener(saveActivity);
        edtHashtag2.addTextChangedListener(saveActivity);
        edtHashtag3.addTextChangedListener(saveActivity);

        swipeRefreshLayout = findViewById(R.id.swipeLayout_update);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handle = new Handler();
                runable = new Runnable() {
                    @Override
                    public void run() {

                        swipeRefreshLayout.setRefreshing(false);
                        handle.removeCallbacks(runable);
                    }
                };
                handle.postDelayed(runable, 1000);
            }
        });
        detail();
        date();
    }

    private TextWatcher saveActivity = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int before, int count) {

            String title = edtTitle.getText().toString().trim();
            String date = Date.getText().toString().trim();
            String text = edtContent.getText().toString().trim();
            String hashtag1 = edtHashtag1.getText().toString().trim();
            String hashtag2 = edtHashtag2.getText().toString().trim();
            String hashtag3 = edtHashtag3.getText().toString().trim();

            btnUpdate.setEnabled(!title.isEmpty() && !date.isEmpty() &&!text.isEmpty() && !hashtag1.isEmpty() && !hashtag2.isEmpty() && !hashtag3.isEmpty());
            btnSend.setEnabled(!title.isEmpty() && !date.isEmpty() &&!text.isEmpty() && !hashtag1.isEmpty() && !hashtag2.isEmpty() && !hashtag3.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(postPath != null){
                        uploadFile();
                    }else
                       edit();
                }
            });
            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    send();
                }
            });
        }
    };



    private void image(String url) {
        Picasso.with(this).load(url)
//                .error(R.mipmap.ic_launcher)
                .into(viewPic, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
    private void date() {
        Date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        UpdateActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth, DateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                dialog.show();
            }
        });

        DisplayDate =  findViewById(R.id.textView_update_date);
        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

//                date = day +" " + monthName +" " +(year+543);
                dateWard = year + "/" + month + "/" + day;
                DisplayDate.setText(dateWard);
            }
        };
    }

    private void detail() {
        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/Diary/DiaryDetail");
        diaryId = getIntent().getExtras().getInt("diaryId");
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
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                jsonStudent = response.body().string();
                try {
                    JSONObject jo = new JSONObject(jsonStudent);
                    diary = gson.fromJson(jsonStudent, Diary.class);


                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String time = diary.getDateward();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        SimpleDateFormat output = new SimpleDateFormat("yyyy/M/dd ");

                        java.util.Date d = null;
                        try {
                            d = sdf.parse(time);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String formattedTime = output.format(d);
                        String title = diary.getDiarytitle();
                        String content = diary.getDiarycontent();
                        ArrayList<HashtagDetail> hashtags = diary.getDiaryHashtagDetails();
                        Object diaryPicture = diary.getDiaryPictureDetials().getPicName();
                        url = String.valueOf(diaryPicture);

                        DisplayDate.setText(formattedTime);
                        edtTitle.setText(title);
                        edtContent.setText(content);
                        edtHashtag1.setText(hashtags.get(0).getHashTagName().toString());
                        edtHashtag2.setText(hashtags.get(1).getHashTagName().toString());
                        edtHashtag3.setText(hashtags.get(2).getHashTagName().toString());
                        image(url);
                    }
                });
            }
        });
    }

    private void edit() {

                HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/Diary/UpdateDiary");
                JSONObject jsonObject = new JSONObject();

                diaryId = getIntent().getExtras().getInt("diaryId");
                String diaryTitle = edtTitle.getText().toString();
                String diaryContent = edtContent.getText().toString();
                String hashTag1 = edtHashtag1.getText().toString();
                String hashTag2 = edtHashtag2.getText().toString();
                String hashTag3 = edtHashtag3.getText().toString();
                String date = DisplayDate.getText().toString();

                String studentId = LocalStorage.getString("id","0");
                subjectId = diary.getSubjectId();

                try {

                    ArrayList<HashtagDetail> hashtags = diary.getDiaryHashtagDetails();
                    ArrayList<HashtagDetail> hashtagDetails = new ArrayList<HashtagDetail>() ;
                    ArrayList<Integer> deleteHashTagList  = new ArrayList<Integer>();
                    ArrayList<String> newHashTagList  = new ArrayList<String>();
                    ArrayList<HashTagCreate> hashtagsCreate = new ArrayList<HashTagCreate>() ;

                        //Tag1
                            if (hashTag1.toString() != edtHashtag1.toString()) {
                                int id = hashtags.get(0).getHashTagId();
                                deleteHashTagList.add(id);
                                newHashTagList.add(hashTag1);
                            }

                        //Tag 2
                            if (hashTag2.toString() != edtHashtag2.toString()) {
                                int id = hashtags.get(1).getHashTagId();
                                deleteHashTagList.add(id);
                                newHashTagList.add(hashTag2);
                            }

                        //Tag 3
                                if (hashTag3.toString() != edtHashtag3.toString()) {
                                    int id = hashtags.get(2).getHashTagId();
                                    deleteHashTagList.add(id);
                                    newHashTagList.add(hashTag3);
                                }


                    diary.setDiaryHashtagDetails(hashtags);
                    diary.setDeleteHashTagList(deleteHashTagList);
                    diary.setNewHashTagList(newHashTagList); ;
                    diary.setDateward(date);
                    diary.setDiarycontent(diaryContent);
                    diary.setDiarytitle(diaryTitle);
                    diary.setStudentId(studentId);
                    diary.setSubjectId(subjectId);
                    diary.setSubjectId(diaryId);
                    diary.setNewPicture(pic);

                    JsonDiary = gson.toJson(diary);
                    JsonParser jsonParser = new JsonParser();
                    objectFromString = jsonParser.parse(JsonDiary).getAsJsonObject();

                    jsonObject.put("DiaryTitle", diaryTitle);
                    jsonObject.put("diaryContent", diaryContent);
                    jsonObject.put("StudentID", studentId);
                    jsonObject.put("SubjectID", subjectId);
                    jsonObject.put("diaryId", diaryId);
                    jsonObject.put("DateWard", date);
                    jsonObject.put("DeleteHashTagList",deleteHashTagList);
                    jsonObject.put("NewHashTagList",newHashTagList);
                    jsonObject.put("NewPicture",pic);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("EIEI", jsonObject.toString());

                JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        final String json = response.body().string();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(UpdateActivity.this, "แก้ไขแล้ว", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_edit_pic:
                new MaterialDialog.Builder(this)
                        .title(R.string.uploadImages)
                        .items(R.array.uploadImage)
                        .itemsIds(R.array.itemId)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        break;
//                                    case 1:
//                                        captureImage();
//                                        break;
                                    case 1:
                                        viewPic.setImageResource(R.color.bluebg);
                                        break;
                                }
                            }
                        })
                        .show();
                break;
        }
    }
    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
                if (data != null) {
                    // Get the Image from data
                    selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    // Set the Image in ImageView for Previewing the Media
                    viewPic.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    cursor.close();

                    postPath = mediaPath;
                }


            }else if (requestCode == CAMERA_PIC_REQUEST){
                if (Build.VERSION.SDK_INT > 21) {

                    Glide.with(this).load(mImageFileLocation).into(viewPic);
                    postPath = mImageFileLocation;

                }else{
                    Glide.with(this).load(fileUri).into(viewPic);
                    postPath = fileUri.getPath();

                }

            }

        }
        else if (resultCode != RESULT_CANCELED) {
            Toast.makeText(this, "Sorry, there was an error!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    protected void initDialog() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.msg_loading));
        pDialog.setCancelable(true);
    }


//    protected void showpDialog() {
//
//        if (!pDialog.isShowing()) pDialog.show();
//    }
//
//    protected void hidepDialog() {
//
//        if (pDialog.isShowing()) pDialog.dismiss();
//    }


    private void captureImage() {
        if (Build.VERSION.SDK_INT > 21) { //use this if Lollipop_Mr1 (API 22) or above
            Intent callCameraApplicationIntent = new Intent();
            callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Logger.getAnonymousLogger().info("Exception error in generating the file");
                e.printStackTrace();
            }
         Uri outputUri = FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    photoFile);
            callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);

            callCameraApplicationIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Logger.getAnonymousLogger().info("Calling the camera App by intent");

            startActivityForResult(callCameraApplicationIntent, CAMERA_PIC_REQUEST);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            startActivityForResult(intent, CAMERA_PIC_REQUEST);
        }
    }

    File createImageFile() throws IOException {
        Logger.getAnonymousLogger().info("Generating the image - method started");

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp;
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/photo_saving_app");
        Logger.getAnonymousLogger().info("Storage directory set");
        if (!storageDirectory.exists()) storageDirectory.mkdir();
        File image = new File(storageDirectory, imageFileName + ".jpg");
        Logger.getAnonymousLogger().info("File name and path set");
        mImageFileLocation = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + ".jpg");
        }  else {
            return null;
        }

        return mediaFile;
    }

    public void uploadFile() {
        Map<String, RequestBody> map = new HashMap<>();
        file = new File(postPath);

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("image",file.getName(),requestBody);

        ApiConfig getResponse = AppConfig.getRetrofit().create(ApiConfig.class);
        retrofit2.Call<ServerResponse> call = getResponse.Upload(multipartBody);

        call.enqueue(new retrofit2.Callback<ServerResponse>() {
            @Override
            public void onFailure(retrofit2.Call<ServerResponse> call, Throwable t) {
//                    hidepDialog();
                Log.v("Response gotten is", t.getMessage());

            }
            @Override
            public void onResponse(retrofit2.Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
//                        hidepDialog();
                        ServerResponse serverResponse = response.body();

                        pic = serverResponse.getFileName();
                        edit();
                    }
                }else {
//                        hidepDialog();
                    Toast.makeText(getApplicationContext(), "problem uploading image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void send() {

                HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/Diary/SuccessDiary");

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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                        Toast.makeText(getApplicationContext(),jsonStudent,Toast.LENGTH_LONG).show();
                                Toast.makeText(UpdateActivity.this, "ส่งแล้ว", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateActivity.this, DiaryListActivity.class);
                                intent.putExtra("subjectId",subjectId);
                                startActivity(intent);
                            }
                        });
                    }
                });
    }
}
