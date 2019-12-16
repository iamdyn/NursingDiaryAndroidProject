package com.example.deanaagirl.NursingDiary.Model;

/**
 * Created by DeanaAgirl on 3/13/2018.
 */

public class HelperSubject {

    private int id;
    private String HelperSubject;

    public HelperSubject(int id, String helperSubject) {
        this.id = id;
        HelperSubject = helperSubject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHelperSubject() {
        return HelperSubject;
    }

    public void setHelperSubject(String helperSubject) {
        HelperSubject = helperSubject;
    }
}

