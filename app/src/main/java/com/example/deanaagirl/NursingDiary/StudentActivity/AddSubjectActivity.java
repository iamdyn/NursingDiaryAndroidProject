package com.example.deanaagirl.NursingDiary.StudentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.deanaagirl.NursingDiary.Connect.JSONObtained;
import com.example.deanaagirl.NursingDiary.Model.Assistant;
import com.example.deanaagirl.NursingDiary.Model.QR;
import com.example.deanaagirl.NursingDiary.Model.Subjects;
import com.example.deanaagirl.NursingDiary.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.Result;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;

import static android.Manifest.permission.CAMERA;

public class AddSubjectActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;

    Gson gson = new GsonBuilder().create();
    Subjects studentData = new Subjects() ;
    String jsonStudent;
    Subjects subjects = new Subjects();

    Subjects datatest = new Subjects();
    List<Subjects> datalist = new ArrayList<>();

    String [] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        int currentApiVersion = Build.VERSION.SDK_INT;

        if(currentApiVersion >=  Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
//                Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }
    }

    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted){
//                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_SHORT).show();
                    }else {
//                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(AddSubjectActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void handleResult(final Result result) {

        final String myResult = result.getText();

        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/Subject/SubjectSelector");
        studentData.setQrcode(myResult);
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
                    subjects = gson.fromJson(jsonStudent, Subjects.class);

                    ArrayList<Assistant> assistants = subjects.getAssistantSelector();

                    list = new String[assistants.size()];
                    for(int i = 0 ; i < assistants.size() ;i++){
                        if (assistants.get(i).getTeacherName() != null){
                          list[i] = assistants.get(i).getTeacherName();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("เลือกอาจารย์ประจำกลุ่ม");
        builder.setIcon(R.drawable.icon);
        builder.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                String teacherId ;
                Intent intent = new Intent(AddSubjectActivity.this,StudenMainActivity.class);
                teacherId = subjects.getAssistantSelector().get(position).getTeacherId();
                intent.putExtra("teacherId",teacherId);
                intent.putExtra("QR Code",myResult);
                startActivity(intent);
            }
        });

        builder.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scannerView.resumeCameraPreview(AddSubjectActivity.this);
            }
        });

        AlertDialog alert1 = builder.create();
        alert1.show();

    }
}
