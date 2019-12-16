package com.example.deanaagirl.NursingDiary.Model;

/**
 * Created by DeanaAgirl on 3/12/2018.
 */

public class HelperStName {
    private int id;
    private String HelpStName;

    public HelperStName(int id, String helpStName) {
        this.id = id;
        HelpStName = helpStName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHelpStName() {
        return HelpStName;
    }

    public void setHelpStName(String helpStName) {
        HelpStName = helpStName;
    }
}
