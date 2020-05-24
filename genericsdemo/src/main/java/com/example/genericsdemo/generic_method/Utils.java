package com.example.genericsdemo.generic_method;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/23 14:19
 * pkn    : com.example.genericsdemo.generic_method
 * desc   :
 */
public class Utils {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }

    public <T extends ViewGroup> T getView(Context context) {
        return (T) new LinearLayout(context);
    }
}
