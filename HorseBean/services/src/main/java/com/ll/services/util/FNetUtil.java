package com.ll.services.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ll.services.FApplication;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FNetUtil
{
    public static boolean isConnected()
    {
        boolean ret = false;
        ConnectivityManager cm = (ConnectivityManager) FApplication.getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != cm)
        {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (null != info)
            {
                if (info.isConnected() && NetworkInfo.State.CONNECTED == info.getState())
                {
                    ret = true;
                }
            }
        }
        return ret;
    }

    public static boolean isWifi()
    {
        boolean ret = false;
        ConnectivityManager cm = (ConnectivityManager) FApplication.getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != cm)
        {
            ret = ConnectivityManager.TYPE_WIFI == cm.getActiveNetworkInfo().getType();
        }
        return ret;
    }
}
