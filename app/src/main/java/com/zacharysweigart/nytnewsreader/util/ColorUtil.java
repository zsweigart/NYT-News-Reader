package com.zacharysweigart.nytnewsreader.util;

import android.graphics.Color;

public class ColorUtil {

    public static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f; // value component
        color = Color.HSVToColor(hsv);
        return color;
    }
}
