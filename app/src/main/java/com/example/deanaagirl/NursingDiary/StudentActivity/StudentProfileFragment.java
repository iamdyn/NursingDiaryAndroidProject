package com.example.deanaagirl.NursingDiary.StudentActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deanaagirl.NursingDiary.ChangePasswordActivity;
import com.example.deanaagirl.NursingDiary.Model.User;
import com.example.deanaagirl.NursingDiary.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentProfileFragment extends Fragment {
    TextView firstname_Text ,lastname_Text , userid_Text;
    Button btn_change_pass;

    User user = new User();
    Gson gson = new GsonBuilder().create();
    String jsonUser;
    SharedPreferences LocalStorage;

//    public StudentProfileFragment() {
//        // Required empty public constructor
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_student_profile, container, false);

        firstname_Text = v.findViewById(R.id.textView_name_st_profile);
        lastname_Text = v.findViewById(R.id.textView_last_st_profile);
        userid_Text = v.findViewById(R.id.textView_id_st_profile);
        btn_change_pass = v.findViewById(R.id.button_change_password);
        btn_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        showtext();
        return v;
    }

    private void showtext() {

        Intent intent = getActivity().getIntent();
        jsonUser = intent.getStringExtra("jsonUser");
        user = gson.fromJson(jsonUser,User.class);

        LocalStorage = getActivity().getSharedPreferences("DATAUSER",0);

        String id = LocalStorage.getString("id","0");
        String firstname = LocalStorage.getString("firstname","0");
        String lastname = LocalStorage.getString("lastname","0");

        firstname_Text.setText(firstname);
        lastname_Text.setText(lastname);
        userid_Text.setText(id);
    }
}
