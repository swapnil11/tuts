package com.example.myapplication;

import com.example.myapplication.model.WeatherData;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by srg11 on 6/25/2016.
 */
public interface RestInterface {

    @GET("/weather")
    void getWheatherReport(@Query("q") String query,@Query("APPID") String apiKey,@Query("units") String tempUnits,
                           Callback<WeatherData> cb);

}
