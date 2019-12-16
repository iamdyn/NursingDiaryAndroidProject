package com.example.deanaagirl.NursingDiary.TeacherActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deanaagirl.NursingDiary.Connect.JSONObtained;
import com.example.deanaagirl.NursingDiary.Model.ChangePassword;
import com.example.deanaagirl.NursingDiary.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;


public class ChangePassFragment extends Fragment {

    SharedPreferences LocalStorage;
    private EditText edtOldpass, edtNewpass;
    private Button btnChange;
    private ChangePassword pass = new ChangePassword();
    private String JsonDiary;
    private JsonObject objectFromString;
    private Gson gson = new GsonBuilder().create();


    public ChangePassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);

        LocalStorage = getActivity().getSharedPreferences("DATAUSER", 0);
        String id = LocalStorage.getString("id", "0");


        edtOldpass = v.findViewById(R.id.editText_Toldpass);
        edtNewpass = v.findViewById(R.id.editText_Tnewpass);
        btnChange = v.findViewById(R.id.button_OK);
        edtNewpass.addTextChangedListener(ChangPass);
        edtOldpass.addTextChangedListener(ChangPass);


        return v;
    }

    private TextWatcher ChangPass = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int before, int count) {
            String oldPass = edtOldpass.getText().toString().trim();
            String newPass = edtNewpass.getText().toString().trim();
            btnChange.setEnabled(!oldPass.isEmpty() && !newPass.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {
            btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "/api/Teacher/ChangePasswordTeacher");
                    JSONObject jsonObject = new JSONObject();

                    String passwordNew = edtNewpass.getText().toString();
                    String passwordOld = edtOldpass.getText().toString();
                    String teacherId = LocalStorage.getString("id","0");

                    try {
                        pass.setPasswordNew(passwordNew);
                        pass.setPasswordOld(passwordOld);
                        pass.setTeacherId(teacherId);

                        JsonDiary = gson.toJson(pass);
                        JsonParser jsonParser = new JsonParser();
                        objectFromString = jsonParser.parse(JsonDiary).getAsJsonObject();


                        jsonObject.put("teacherId", teacherId);
                        jsonObject.put("passwordNew", passwordNew);
                        jsonObject.put("passwordOld", passwordOld);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, final IOException e) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                                    if (reback == false){
                                        Toast.makeText(getContext(), "รหัสผ่านเก่าไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(getActivity(), "เปลี่ยนรหัสผ่านแล้ว", Toast.LENGTH_SHORT).show();
                                    }
//                                      Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                }
            });
        }
    };
}
