package com.example.sitectestapp.data.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "responses")
public class ResponsesEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id = 0;

    private String response;

    private String continueWork;

    private String photoHash;

    private String currentDate;

    public int getId() {
        return id;
    }

    public String getContinueWork() {
        return continueWork;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getPhotoHash() {
        return photoHash;
    }

    public String getResponse() {
        return response;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContinueWork(String continueWork) {
        this.continueWork = continueWork;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public void setPhotoHash(String photoHash) {
        this.photoHash = photoHash;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
