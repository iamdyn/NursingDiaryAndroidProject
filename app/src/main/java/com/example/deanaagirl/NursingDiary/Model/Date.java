package com.example.deanaagirl.NursingDiary.Model;

/**
 * Created by DeanaAgirl on 3/10/2018.
 */

public class Date {
    private int id;
    private String DateOnWard;

    public Date(int id, String dateOnWard) {
        this.id = id;
        DateOnWard = dateOnWard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOnWard() {
        return DateOnWard;
    }

    public void setDateOnWard(String dateOnWard) {
        DateOnWard = dateOnWard;
    }
}
