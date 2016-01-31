package com.zacharysweigart.newscard;


import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Util {
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}
