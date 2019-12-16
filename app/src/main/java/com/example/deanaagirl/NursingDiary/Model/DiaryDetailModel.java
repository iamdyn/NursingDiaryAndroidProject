package com.example.deanaagirl.NursingDiary.Model;

import java.util.ArrayList;

public class DiaryDetailModel {
    private int diaryId;
    private String diaryTitle;
    private String diaryContent;
    private String dateSend;
    private String dateWardToString;
    private String dateCreateToString;
    private String dateWard;
    private String dateCreate;
    private String dateUpdate;
    private int statusId;
    private String studentId;
    private int subjectId;
    private String subjectName;
    private String teacherId;
    private ArrayList<HashTagCreate> hashTagList;
    private ArrayList<HashtagDetail> diaryHashtagDetails;
    private ArrayList<String> NewHashTagList;
    private ArrayList<Integer> DeleteHashTagList;
    private DiaryPictureDetials diaryPictureDetials;

    public DiaryPictureDetials getDiaryPictureDetials() {
        return diaryPictureDetials;
    }

    public void setDiaryPictureDetials(DiaryPictureDetials diaryPictureDetials) {
        this.diaryPictureDetials = diaryPictureDetials;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }

    public String getDiaryContent() {
        return diaryContent;
    }

    public void setDiaryContent(String diaryContent) {
        this.diaryContent = diaryContent;
    }

    public String getDateSend() {
        return dateSend;
    }

    public void setDateSend(String dateSend) {
        this.dateSend = dateSend;
    }

    public String getDateWardToString() {
        return dateWardToString;
    }

    public void setDateWardToString(String dateWardToString) {
        this.dateWardToString = dateWardToString;
    }

    public String getDateCreateToString() {
        return dateCreateToString;
    }

    public void setDateCreateToString(String dateCreateToString) {
        this.dateCreateToString = dateCreateToString;
    }

    public String getDateWard() {
        return dateWard;
    }

    public void setDateWard(String dateWard) {
        this.dateWard = dateWard;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public ArrayList<HashTagCreate> getHashTagList() {
        return hashTagList;
    }

    public void setHashTagList(ArrayList<HashTagCreate> hashTagList) {
        this.hashTagList = hashTagList;
    }

    public ArrayList<HashtagDetail> getDiaryHashtagDetails() {
        return diaryHashtagDetails;
    }

    public void setDiaryHashtagDetails(ArrayList<HashtagDetail> diaryHashtagDetails) {
        this.diaryHashtagDetails = diaryHashtagDetails;
    }

    public ArrayList<String> getNewHashTagList() {
        return NewHashTagList;
    }

    public void setNewHashTagList(ArrayList<String> newHashTagList) {
        NewHashTagList = newHashTagList;
    }

    public ArrayList<Integer> getDeleteHashTagList() {
        return DeleteHashTagList;
    }

    public void setDeleteHashTagList(ArrayList<Integer> deleteHashTagList) {
        DeleteHashTagList = deleteHashTagList;
    }

}
