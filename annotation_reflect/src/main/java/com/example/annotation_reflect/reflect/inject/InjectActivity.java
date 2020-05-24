package com.example.annotation_reflect.reflect.inject;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/26 22:44
 * pkn    : com.example.annotation_reflect.reflect.inject
 * desc   :
 */
public class InjectActivity {
    public static void injectActivity(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindField.class)) {
                BindField annotation = field.getAnnotation(BindField.class);
                String name = annotation.name();
                String type = field.getGenericType().toString();// 获取属性的类型
                Log.e("InjectActivity",type);
                field.setAccessible(true);
                Object extra = null;
                if (type.equals("class java.lang.String")) {
                    extra = activity.getIntent().getStringExtra(name);
                }
                if (type.equals("int")) {
                    extra = activity.getIntent().getIntExtra(name, 0);
                }
                // 其他类型.............
                try {
                    field.set(activity, extra);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
