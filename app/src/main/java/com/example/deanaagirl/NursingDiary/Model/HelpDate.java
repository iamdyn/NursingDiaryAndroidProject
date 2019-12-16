package com.example.deanaagirl.NursingDiary.Model;

/**
 * Created by DeanaAgirl on 3/12/2018.
 */

public class HelpDate {
    private int id;
    private String HelpDate;

    public HelpDate(int id, String helpDate) {
        this.id = id;
        HelpDate = helpDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHelpDate() {
        return HelpDate;
    }

    public void setHelpDate(String helpDate) {
        HelpDate = helpDate;
    }
}
