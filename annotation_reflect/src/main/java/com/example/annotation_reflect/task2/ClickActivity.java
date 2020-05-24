package com.example.annotation_reflect.task2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.annotation_reflect.R;
import com.example.annotation_reflect.task2.annotation.OnClick;
import com.example.annotation_reflect.task2.annotation.OnClickListener;
import com.example.annotation_reflect.task2.reflect.InjectUtils;
import com.example.annotation_reflect.task2.reflect.OnClickInject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/29 17:43
 * pkn    : com.example.annotation_reflect.task2
 * desc   :
 */
public class ClickActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        InjectClick.injectOnClick(this);
//        OnClickInject.injectOnClick(this);
        InjectUtils.injectEvent(this);
    }



    @OnClick({R.id.click1, R.id.click2, R.id.click3})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.click1:
                Log.e("OnClick", "点击事件1");
                break;
            case R.id.click2:
                Log.e("OnClick", "点击事件2");
                break;
            case R.id.click3:
                Log.e("OnClick", "点击事件3");
                break;
        }
    }
}
