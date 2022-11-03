package com.example.sitectestapp.data.response;

import com.google.gson.annotations.SerializedName;

public class Authentication {
    @SerializedName("Response")
    public String response;

    @SerializedName("ContinueWork")
    public String continueWork;

    @SerializedName("PhotoHash")
    public String photoHash;

    @SerializedName("CurrentDate")
    public String currentDate;
}
