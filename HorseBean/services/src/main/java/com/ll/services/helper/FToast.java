package com.ll.services.helper;

import android.widget.Toast;

import com.ll.services.FApplication;

public class FToast
{
    public static void showShort(CharSequence text)
    {
        Toast.makeText(FApplication.getAppContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(int resId)
    {
        Toast.makeText(FApplication.getAppContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(CharSequence text)
    {
        Toast.makeText(FApplication.getAppContext(), text, Toast.LENGTH_LONG).show();
    }

    public static void showLong(int resId)
    {
        Toast.makeText(FApplication.getAppContext(), resId, Toast.LENGTH_LONG).show();
    }

    public static void show(CharSequence text, int duration)
    {
        Toast.makeText(FApplication.getAppContext(), text, duration).show();
    }

    public static void show(int resId, int duration)
    {
        Toast.makeText(FApplication.getAppContext(), resId, duration).show();
    }
}
