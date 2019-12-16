package com.example.deanaagirl.NursingDiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deanaagirl.NursingDiary.Connect.JSONObtained;
import com.example.deanaagirl.NursingDiary.Model.Login;
import com.example.deanaagirl.NursingDiary.Model.User;
import com.example.deanaagirl.NursingDiary.StudentActivity.StudenMainActivity;
import com.example.deanaagirl.NursingDiary.TeacherActivity.TeacherMainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;

public class LoginActivity extends AppCompatActivity {

    Button btn_signin;
    EditText edtUserid, edtPassword;
    User user = new User();
    Login login = new Login();
    Gson gson = new GsonBuilder().create();
    String jsonUser;
    String jsonLogin ;
    String JsonData;
    SharedPreferences LocalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LocalStorage = getSharedPreferences("DATAUSER",0);
        bindWidget();
        loginActivity();
    }

    private void loginActivity() {
        btn_signin = findViewById(R.id.button_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty()) {
                    LogData();
                }
            }
        });
    }

    private boolean checkEmpty() {

        if (edtPassword.getText().length() == 0 && edtUserid.getText().length() == 0) {
            Toast.makeText(LoginActivity.this, "กรุณาใส่ชื่อผู้ใช้และรหัสผ่าน", Toast.LENGTH_SHORT).show();
            return false;

        } else if (edtPassword.getText().length() == 0) {
            Toast.makeText(LoginActivity.this, "กรุณาใส่รหัสผ่าน", Toast.LENGTH_SHORT).show();
            return false;

        } else if (edtUserid.getText().length() == 0) {
            Toast.makeText(LoginActivity.this, "กรุณาใส่ชื่อผู้ใช้", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void bindWidget() {
        edtUserid = findViewById(R.id.editText_username);
        edtPassword = findViewById(R.id.editText_password);
    }

    public void LogData() {
        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "api/User/Login");

        String userid = edtUserid.getText().toString();
        String password = edtPassword.getText().toString();

        login.setUserId(edtUserid.getText().toString());
        login.setPassword(edtPassword.getText().toString());
        jsonLogin = gson.toJson(login);
        JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(jsonLogin).getAsJsonObject();


            try {

            } catch (Exception e) {
        e.printStackTrace();
    }

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
                 jsonUser = response.body().string();
                try {
                    JSONObject jo = new JSONObject(jsonUser);

                    user = gson.fromJson(jsonUser , User.class);

                    SharedPreferences.Editor editor = LocalStorage.edit();
                    String id = user.getUserId();
                    String firstname = user.getFirstname();
                    String lastname = user.getLastname();

                    editor.putString("id",id);
                    editor.putString("firstname",firstname);
                    editor.putString("lastname",lastname);
                    editor.apply();
                    }
                    catch (JSONException e) {

                    }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jObj = new JSONObject(jsonLogin);
                            if (user.getUserId() != null){
                                if (user.getTypeId() == 303) {
                                    Toast.makeText(getApplicationContext(), jsonUser, Toast.LENGTH_LONG).show();
                                    Intent login = new Intent(LoginActivity.this, StudenMainActivity.class);
                                    startActivity(login);
                                }
                                if (user.getTypeId() == 202) {
                                    Toast.makeText(getApplicationContext(), jsonUser, Toast.LENGTH_LONG).show();
                                    Intent login = new Intent(LoginActivity.this, TeacherMainActivity.class);
                                    startActivity(login);
                                }
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}