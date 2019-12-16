package com.example.deanaagirl.NursingDiary.Service;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiConfig {
    @Multipart
    @POST("api/Upload/Upload")
    Call<ServerResponse> Upload(
            // @Header("Authorization") String authorization,
            //@PartMap Map<String, RequestBody> map
            @Part MultipartBody.Part file

    );
}
