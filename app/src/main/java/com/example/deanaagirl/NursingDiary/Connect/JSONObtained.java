package com.example.deanaagirl.NursingDiary.Connect;

import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JSONObtained {

//    public static final String BASE_URL = "http://nursingdiaryapi.ap-southeast-1.elasticbeanstalk.com/";
//    public static final String BASE_URL = "http://nursing-diary-api.ap-southeast-1.elasticbeanstalk.com/"; // kirkpoltawonkangwal2539
//    public static final String BASE_URL = "http://api-nursing-diary.ap-southeast-1.elasticbeanstalk.com/";  // 580107030013
//    public static final String BASE_URL = "http://nursingdiarywebapi-dev.ap-southeast-1.elasticbeanstalk.com/";
    public static final String BASE_URL = "http://nursingdiarywebapi-dev.ap-southeast-1.elasticbeanstalk.com/";

    private static OkHttpClient client = null;

    public static OkHttpClient getInstance() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)// connect timeout
                    .writeTimeout(200, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS) // socket timeout
                    .build();
        }

        return client;
    }

    public static Request getRequest(HttpUrl url) {

        Request request = new Request.Builder()
                .url(url)
                .build();
        return request;
    }

    public static Request postRequest(HttpUrl url, RequestBody formBody) {

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        return request;
    }

    public static Request postJSON (HttpUrl url, JsonObject jsonObject)
    {
        MediaType mt = MediaType.parse("application/json; charset=utf-8");
        String json = jsonObject.toString();

        RequestBody body = RequestBody.create(mt, json);

        Request req = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return req;
    }

    public static String getAbsoluteUrl(String relativeUrl) {

        return BASE_URL + relativeUrl;
    }

}