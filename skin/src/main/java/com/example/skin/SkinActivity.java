package com.example.skin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.skinlib.SkinManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/**
 * @author 享学课堂 jett
 */

public class SkinActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LayoutInflater.from(this).setFactory2();
        setContentView(R.layout.activity_skin);

//        findViewById(R.id.tabLayout);
//        Resources resources = getResources();
//        new Resources()


        findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SkinActivity.this, TestThirdActivity.class));
            }
        });

    }

    public void change(View view) {
        //换肤，收包裹，皮肤包是独立的apk包，可以来自网络下载
        SkinManager.getInstance().loadSkin("/data/data/com.enjoy.skin/skin/skin-debug.apk");
    }

    public void restore(View view) {
        SkinManager.getInstance().loadSkin(null);
    }
}
