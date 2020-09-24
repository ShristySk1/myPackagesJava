package com.ayata.mypackagesjava.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface myApi {
    @GET("api/")
    Call<Weather> getWeatherDetail(@Query("place") String place);
}
