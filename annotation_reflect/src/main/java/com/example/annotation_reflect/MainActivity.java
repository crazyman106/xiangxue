package com.example.annotation_reflect;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.annotation_reflect.annotation.Lance;
import com.example.annotation_reflect.reflect.inject.Retrofit.WeatherRetrofit;
import com.example.annotation_reflect.reflect.inject.Retrofit.api.CustomWeatherApi;
import com.example.annotation_reflect.task2.ClickActivity;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

@Lance
public class MainActivity extends AppCompatActivity {

    CustomWeatherApi enjoyWeatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Intent intent = new Intent(this, SecondActivity.class)
                .putExtra("name", "lijunjie")
                .putExtra("sex", "男")
                .putExtra("age", 28);
        startActivity(intent);*/

        Intent intent = new Intent(this, ClickActivity.class);
        startActivity(intent);

        WeatherRetrofit enjoyRetrofit = new WeatherRetrofit.Builder().baseUrl("https://restapi.amap.com").build();
        enjoyWeatherApi = enjoyRetrofit.create(CustomWeatherApi.class);
    }

    public void enjoyGet(View view) {
        enjoyWeatherApi.getWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b").enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                response.close();
            }
        });

        Integer[] o = (Integer[]) Array.newInstance(Integer.class, 10);
    }

    public void enjoyPost(View view) {
        enjoyWeatherApi.postWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b").enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                response.close();
            }
        });

    }



}
