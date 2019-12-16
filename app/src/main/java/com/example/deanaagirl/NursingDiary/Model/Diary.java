package com.example.deanaagirl.NursingDiary.Model;


import java.io.File;
import java.util.ArrayList;

/**
 * Created by DeanaAgirl on 3/10/2018.
 */

public class Diary {

    private String diaryTitle;
    private String diaryContent;
    private String dateCreate;
    private String dateWard;
    private int statusId;
    private String studentId;
    private int subjectId;
    private String teacherId;
    private String studentName;
    private int diaryId;
    private File image;
    private String dateWardToString;
    private String dateCreateToString;
    private ArrayList<HashTagCreate> hashTagList;
    private ArrayList<HashtagDetail> diaryHashtagDetails;
    private ArrayList<String> NewHashTagList;
    private ArrayList<Integer> DeleteHashTagList;
    private DiaryPictureDetials diaryPictureDetials;

    private File uploadImage;
    private String pictureName;
    private String dateString;

    private String NewPicture;

    public String getNewPicture() {
        return NewPicture;
    }

    public void setNewPicture(String newPicture) {
        NewPicture = newPicture;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public File getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(File uploadImage) {
        this.uploadImage = uploadImage;
    }

    public ArrayList<String> getNewHashTagList() {
        return NewHashTagList;
    }

    public void setNewHashTagList(ArrayList<String> newHashTagList) {
        NewHashTagList = newHashTagList;
    }

    public ArrayList<Integer> getDeleteHashTagList(ArrayList<Integer> deleteHashTagList) {
        return DeleteHashTagList;
    }

    public void setDeleteHashTagList(ArrayList<Integer> deleteHashTagList) {
        DeleteHashTagList = deleteHashTagList;
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

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public ArrayList<HashtagDetail> getDiaryHashtagDetails() {
        return diaryHashtagDetails;
    }

    public void setDiaryHashtagDetails(ArrayList<HashtagDetail> diaryHashtagDetails) {
        this.diaryHashtagDetails = diaryHashtagDetails;
    }

    public ArrayList<HashTagCreate> getHashTagList() {
        return hashTagList;
    }

    public void setHashTagList(ArrayList<HashTagCreate> hashTagList) {
        this.hashTagList = hashTagList;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDiarytitle() {
        return diaryTitle;
    }

    public void setDiarytitle(String diarytitle) {
        this.diaryTitle = diarytitle;
    }

    public String getDiarycontent() {
        return diaryContent;
    }

    public void setDiarycontent(String diarycontent) {
        this.diaryContent = diarycontent;
    }

    public String getDatecreate() {
        return dateCreate;
    }

    public void setDatecreate(String datecreate) {
        this.dateCreate = datecreate;
    }

    public String getDateward() {
        return dateWard;
    }

    public void setDateward(String dateward) {
        this.dateWard = dateward;
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

    public DiaryPictureDetials getDiaryPictureDetials() {
        return diaryPictureDetials;
    }

    public void setDiaryPictureDetials(DiaryPictureDetials diaryPictureDetials) {
        this.diaryPictureDetials = diaryPictureDetials;
    }
}

