package com.ll.services.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;

import com.ll.services.FApplication;

import java.util.List;

public class FProcessUtil
{
    public static String getProcessName(int pid)
    {
        ActivityManager am = (ActivityManager) FApplication.getAppContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null)
        {
            return null;
        }
        for (RunningAppProcessInfo appProcess : runningApps)
        {
            if (appProcess.pid == pid)
            {
                return appProcess.processName;
            }
        }
        return null;
    }

    public boolean isAppOnForeground()
    {
        ActivityManager am = (ActivityManager) FApplication.getAppContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null)
        {
            return false;
        }
        String packageName = FApplication.getAppContext().getPackageName();
        for (RunningAppProcessInfo appProcess : runningApps)
        {
            if (appProcess.processName.equals(packageName) &&
                    appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
            {
                return true;
            }
        }
        return false;
    }
}
