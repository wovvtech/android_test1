package com.wovvtech.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainResponse {
    @SerializedName("hits")
    ArrayList<ResponseData> responseDataArrayList;

    public ArrayList<ResponseData> getResponseDataArrayList() {
        return responseDataArrayList;
    }

    public void setResponseDataArrayList(ArrayList<ResponseData> responseDataArrayList) {
        this.responseDataArrayList = responseDataArrayList;
    }
}
