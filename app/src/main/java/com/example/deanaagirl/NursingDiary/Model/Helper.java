package com.example.deanaagirl.NursingDiary.Model;

/**
 * Created by DeanaAgirl on 3/10/2018.
 */

public class Helper {
    private int id;
    private String helper;

    public Helper(int id, String helper) {
        this.id = id;
        this.helper = helper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }
}
