package com.example.annotation_reflect.reflect.inject.Retrofit.api;

import com.example.annotation_reflect.reflect.inject.Retrofit.retrofit.annotation.Field;
import com.example.annotation_reflect.reflect.inject.Retrofit.retrofit.annotation.GET;
import com.example.annotation_reflect.reflect.inject.Retrofit.retrofit.annotation.POST;
import com.example.annotation_reflect.reflect.inject.Retrofit.retrofit.annotation.Query;

import okhttp3.Call;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/6 21:34
 * pkn    : com.example.annotation_reflect.reflect.inject.Retrofit.api
 * desc   :
 */
public interface CustomWeatherApi {
    @POST("/v3/weather/weatherInfo")
    Call postWeather(@Field("city") String city, @Field("key") String key);

    @GET("/v3/weather/weatherInfo")
    Call getWeather(@Query("city") String city, @Query("key") String key);
}
