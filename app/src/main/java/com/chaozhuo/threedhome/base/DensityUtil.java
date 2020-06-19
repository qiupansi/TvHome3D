package com.chaozhuo.threedhome.base;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.chaozhuo.threedhome.App;


/**
 * @version 1.0
 * @author: john
 * @date： 2015/11/25
 * @description: Screen adaptation, resolution conversion
 */
public class DensityUtil {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(float dpValue) {
        final float scale = App.app.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int width() {
        return App.app.getResources().getDisplayMetrics().widthPixels;
    }

    public static int widthReal() {
        try {
            WindowManager wm = (WindowManager) App.app.getSystemService(Context.WINDOW_SERVICE);
            Display defaultDisplay = wm.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            return point.y;
        } catch (Exception e) {
        }
        return width();
    }

    public static int height() {
        return App.app.getResources().getDisplayMetrics().heightPixels;
    }
}