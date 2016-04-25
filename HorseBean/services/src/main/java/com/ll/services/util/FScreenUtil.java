package com.ll.services.util;

import android.util.DisplayMetrics;

import com.ll.services.FApplication;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FScreenUtil
{
    public static int getScreenWidth()
    {
        DisplayMetrics displayMetrics =
                FApplication.getAppContext().getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight()
    {
        DisplayMetrics displayMetrics =
                FApplication.getAppContext().getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static int getStatusHeight()
    {
        int statusHeight = -1;
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height =
                    Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight =
                    FApplication.getAppContext().getResources().getDimensionPixelSize(height);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
