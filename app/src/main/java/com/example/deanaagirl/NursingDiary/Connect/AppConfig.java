package com.example.deanaagirl.NursingDiary.Connect;

import com.example.deanaagirl.NursingDiary.Service.ApiConfig;
import com.google.gson.JsonObject;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppConfig {

    public static String BASE_URL = "http://nursingdiarywebapi-dev.ap-southeast-1.elasticbeanstalk.com/";

    public static Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
