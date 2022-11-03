package com.example.sitectestapp.data.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id = 0;

    private String user;

    private String uid;

    private String language;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public String getUid() {
        return uid;
    }

    public String getLanguage() {
        return language;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
