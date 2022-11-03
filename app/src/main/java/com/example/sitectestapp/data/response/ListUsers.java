package com.example.sitectestapp.data.response;

import com.google.gson.annotations.SerializedName;

public class ListUsers {
    @SerializedName("User")
    public String user;

    @SerializedName("Uid")
    public String uid;

    @SerializedName("Language")
    public String language;
}
