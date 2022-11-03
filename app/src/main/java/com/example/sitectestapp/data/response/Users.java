package com.example.sitectestapp.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Users {

    @SerializedName("ListUsers")
    public List<ListUsers> listUsers;

    @SerializedName("CurrentUid")
    public String currentUid;
}
