package com.xishan.smartapicaller;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search/repositories")
    Call<GitResponse> getRepo(@Query("q") String trending, @Query("sort") String stars);
}
