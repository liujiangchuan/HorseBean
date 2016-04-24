package com.ll.horsebean;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.ll.services.FApplication;
import com.ll.services.FConfig;
import com.ll.services.helper.FLog;

public final class MyConfig
{
    //channel
    public static String CHANNEL = "c00000000";

    public void init()
    {
        try
        {
            PackageManager packageManager = MyApp.getAppContext().getPackageManager();
            ApplicationInfo applicationInfo = packageManager
                    .getApplicationInfo(FApplication.getAppContext().getPackageName(),
                            PackageManager.GET_META_DATA);
            CHANNEL = applicationInfo.metaData.getString("CHANNEL");
            FConfig.FLOG_DEBUG = applicationInfo.metaData.getBoolean("FLOG_DEBUG");
            FConfig.FLOG_DEBUG_LEVEL = applicationInfo.metaData.getInt("FLOG_DEBUG_LEVEL");
            FConfig.FLOG_OUT_TO_FILE = applicationInfo.metaData.getBoolean("FLOG_OUT_TO_FILE");
            FConfig.FLOG_OUT_TO_FILE_LEVEL =
                    applicationInfo.metaData.getInt("FLOG_OUT_TO_FILE_LEVEL");
            FConfig.CRASH_CATCH = applicationInfo.metaData.getBoolean("CRASH_CATCH");
            FConfig.STRICT_MODE = applicationInfo.metaData.getBoolean("STRICT_MODE");
            FConfig.LEAK_CANARY = applicationInfo.metaData.getBoolean("LEAK_CANARY");

            FLog.i("[CHANNEL]:" + CHANNEL + ", [FLOG_DEBUG]:" + FConfig.FLOG_DEBUG +
                    ", [FLOG_DEBUG_LEVEL]:" + FConfig.FLOG_DEBUG_LEVEL + ", [FLOG_OUT_TO_FILE]:" +
                    FConfig.FLOG_OUT_TO_FILE + ", [FLOG_OUT_TO_FILE_LEVEL]:" +
                    FConfig.FLOG_OUT_TO_FILE_LEVEL +
                    ", [CRASH_CATCH]:" + FConfig.CRASH_CATCH + ", [STRICT_MODE]:" +
                    FConfig.STRICT_MODE +
                    ", [LEAK_CANARY]:" + FConfig.LEAK_CANARY);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
