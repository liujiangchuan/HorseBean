package com.ll.services.helper;

import android.app.Application;

import com.ll.services.FConfig;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2016/4/19.
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
