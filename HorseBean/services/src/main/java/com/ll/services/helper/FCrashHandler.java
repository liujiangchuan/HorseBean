package com.ll.services.helper;

import com.ll.services.FConfig;
import com.ll.services.util.FAppUtil;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FCrashHandler implements UncaughtExceptionHandler
{
    private UncaughtExceptionHandler mDefaultHandler;

    private static class SingletonClassInstance
    {
        private static final FCrashHandler self = new FCrashHandler();
    }

    public static FCrashHandler getInstance()
    {
        return SingletonClassInstance.self;
    }

    public void init()
    {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override public void uncaughtException(Thread thread, Throwable ex)
    {
        if (FConfig.CRASH_CATCH)
        {
            FAppUtil.exitApp();
            /**
             * restart. Be careful the crash happened on main activity starting.
             *
             *  Intent intent = new Intent();
             intent.setClass(FApp.getAppContext(), MainActivity.class);
             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             FApp.getAppContext().startActivity(intent);
             android.os.Process.killProcess(android.os.Process.myPid());
             */
        }
        else
        {
            if (null != mDefaultHandler)
            {
                mDefaultHandler.uncaughtException(thread, ex);
            }
        }
    }
}
