package com.example.deanaagirl.NursingDiary.TeacherActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class Tab2 extends Fragment {
    private ListView lvNameSt;
    private StudentNameListAapter adapter;

    Diary datatest = new Diary();
    List<Diary> datalist = new ArrayList<>();
    Diary teachertData = new Diary() ;

    int subjectId;

    String sortbydate;

    String jsonTeacher;
    Gson gson = new GsonBuilder().create();
    SharedPreferences LocalStorage;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab2() {
        // Required empty public constructor
    }

    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab2, container, false);

        LocalStorage = getActivity().getSharedPreferences("DATAUSER",0);
        subjectId = getActivity().getIntent().getExtras().getInt("subjectId");
        sortbydate = getActivity().getIntent().getExtras().getString("date");

        not_send();

        lvNameSt = v.findViewById(R.id.listview_student_not_send);
//        lvNameSt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(view.getContext(), CheckDiaryActivity.class);
//                int diaryid = datalist.get(position).getDiaryId();
//                intent.putExtra("diaryId",diaryid);
//                startActivity(intent);
//            }
//        });
        return v;
    }

    private void not_send() {


        HttpUrl httpUrl = HttpUrl.parse(JSONObtained.BASE_URL + "/api/Diary/ DiarySortByDate");

        teachertData.setTeacherId(LocalStorage.getString("id","0"));
        teachertData.setSubjectId(subjectId);
        teachertData.setDateString(sortbydate);
        jsonTeacher = gson.toJson(teachertData);
        final JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(jsonTeacher).getAsJsonObject();

        JSONObtained.getInstance().newCall(JSONObtained.postJSON(httpUrl, objectFromString)).enqueue(new Callback()  {
            @Override
            public void onFailure(okhttp3.Call call, final IOException e) {

                getActivity().runOnUiThread(new Runnable() {
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
                        if (datatest.getStatusId() == 200) {
                            datalist.add(datatest);
                        }else {

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getApplicationContext(),jsonTeacher,Toast.LENGTH_LONG).show();
                        try {
                            String s = gson.toJson(datalist);
                            adapter = new StudentNameListAapter(getContext(),datalist);
                            lvNameSt.setAdapter(adapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
