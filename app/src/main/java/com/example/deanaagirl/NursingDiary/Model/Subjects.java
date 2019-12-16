package com.example.deanaagirl.NursingDiary.Model;

import java.util.ArrayList;

/**
 * Created by DeanaAgirl on 3/7/2018.
 */

public class Subjects {

    private int id;
    private String studentId;
    private String subjectName;
    private String teacherName;
    private String teacherId;
    private int subjectId;
    private String qrcode;
    private String teacherCreateId;
    private String teacherCreateName;
    private String teacherCreateType;
    private ArrayList<Assistant> assistantSelector;

    public String getTeacherCreateId() {
        return teacherCreateId;
    }

    public void setTeacherCreateId(String teacherCreateId) {
        this.teacherCreateId = teacherCreateId;
    }

    public String getTeacherCreateName() {
        return teacherCreateName;
    }

    public void setTeacherCreateName(String teacherCreateName) {
        this.teacherCreateName = teacherCreateName;
    }

    public String getTeacherCreateType() {
        return teacherCreateType;
    }

    public void setTeacherCreateType(String teacherCreateType) {
        this.teacherCreateType = teacherCreateType;
    }

    public ArrayList<Assistant> getAssistantSelector() {
        return assistantSelector;
    }

    public void setAssistantSelector(ArrayList<Assistant> assistantSelector) {
        this.assistantSelector = assistantSelector;
    }

    public Subjects() {
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}

