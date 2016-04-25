package com.ll.services;

import android.app.Application;

import com.ll.services.helper.FLog;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FApplication extends Application
{
    private static FApplication instance = new FApplication();

    public static FApplication getAppContext()
    {
        return instance;
    }

    @Override public void onCreate()
    {
        super.onCreate();
        instance = this;
    }

    @Override public void onLowMemory()
    {
        super.onLowMemory();
        FLog.e("onLowMemory !!");
    }

    @Override public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);
        FLog.w("onTrimMemory...level: " + level);
        switch (level)
        {
            case TRIM_MEMORY_COMPLETE:
                break;
            default:
                break;
        }
    }

    @Override public void onTerminate()
    {
        super.onTerminate();
        FLog.d("onTerminate !!");
    }
}
