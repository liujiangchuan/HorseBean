package com.ll.services.helper;

import android.app.Application;

import com.ll.services.FConfig;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FLeakCanaryHelper
{
    public void install(Application application)
    {
        if (FConfig.LEAK_CANARY)
        {
            LeakCanary.install(application);
        }
    }
}
