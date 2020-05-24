package com.example.annotation_reflect;

import android.os.Bundle;

import com.example.annotation_reflect.reflect.inject.BindField;
import com.example.annotation_reflect.reflect.inject.InjectActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/26 23:04
 * pkn    : com.example.annotation_reflect
 * desc   :
 */
public class SecondActivity extends AppCompatActivity {

    @BindField(name = "name")
    String name;
    @BindField(name = "age")
    int age;
    @BindField(name = "sex")
    String sex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectActivity.injectActivity(this);
        System.out.println(name + "--" + age + "--" + sex);
    }
}
