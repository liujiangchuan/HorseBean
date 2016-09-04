package com.ll.services.helper;

import android.content.Context;

import com.ll.services.FApplication;
import com.ll.services.FConfig;
import com.umeng.analytics.MobclickAgent;


/**
 * Created by Liujc on 2016/9/4.
 * Email: liujiangchuan@hotmail.com
 */
public class FStatisticAgent
{
    public static void onResume(Context context)
    {
        if (FConfig.STATISTIC)
        {
            try
            {
                MobclickAgent.onResume(context);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void onPause(Context context)
    {
        if (FConfig.STATISTIC)
        {
            try
            {
                MobclickAgent.onPause(context);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void onEvent(String msg)
    {
        if (FConfig.STATISTIC)
        {
            try
            {
                MobclickAgent.onEvent(FApplication.getAppContext(), msg);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
