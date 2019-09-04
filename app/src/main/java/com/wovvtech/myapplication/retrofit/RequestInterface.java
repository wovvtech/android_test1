package com.wovvtech.myapplication.retrofit;

import com.wovvtech.myapplication.model.MainResponse;
import com.wovvtech.myapplication.model.ResponseData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {

    @GET("search_by_date")
    Call<MainResponse> getJSON(@Query("tags")String tags, @Query("page")String page);
}
