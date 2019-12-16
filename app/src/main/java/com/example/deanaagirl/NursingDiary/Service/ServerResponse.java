package com.example.deanaagirl.NursingDiary.Service;

public class ServerResponse {
    boolean success;
    //    @SerializedName("fileName")
    String fileName;

    public String getFileName() {
        return fileName;
    }

    public boolean getSuccess() {
        return success;
    }
}
