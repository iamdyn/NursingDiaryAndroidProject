package com.example.deanaagirl.NursingDiary.Model;

/**
 * Created by DeanaAgirl on 3/10/2018.
 */

public class NameST {
    private int id;
    private String NameST;

    public NameST(int id, String nameST) {
        this.id = id;
        NameST = nameST;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameST() {
        return NameST;
    }

    public void setNameST(String nameST) {
        NameST = nameST;
    }
}
