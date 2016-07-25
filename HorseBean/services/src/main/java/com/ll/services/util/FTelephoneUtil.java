package com.ll.services.util;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.ll.services.FApplication;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FTelephoneUtil
{
    public static String getDeviceId()
    {
        TelephonyManager telephonyManager = (TelephonyManager) FApplication.getAppContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}
