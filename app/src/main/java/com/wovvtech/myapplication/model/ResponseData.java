package com.wovvtech.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("title")
    String title;

    @SerializedName("created_at")
    String created_at;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
