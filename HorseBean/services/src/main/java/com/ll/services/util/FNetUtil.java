package com.ll.services.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

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

    public static String getIP()
    {
        WifiManager wifiManager =
                (WifiManager) FApplication.getAppContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled())
        {
            wifiManager.setWifiEnabled(true);
        }

        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        StringBuilder ip = new StringBuilder();
        ip.append(ipAddress & 0xFF).append(".");
        ip.append((ipAddress >> 8) & 0xFF).append(".");
        ip.append((ipAddress >> 16) & 0xFF).append(".");
        ip.append((ipAddress >> 24) & 0xFF);
        return ip.toString();
    }
}
