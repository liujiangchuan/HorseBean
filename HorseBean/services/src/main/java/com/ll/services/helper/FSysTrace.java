package com.ll.services.helper;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Trace;

import com.ll.services.FConfig;

/**
 * Created by Liujc on 2016/11/15.
 * Email: liujiangchuan@hotmail.com
 */
public class FSysTrace
{
    private static final String DEFAULT_POS = "Method is NOT found!!";

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2) public static void begin(String sectionName)
    {
        if (FConfig.SYS_TRACE)
        {
            Trace.beginSection(sectionName);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2) public static void begin()
    {
        if (FConfig.SYS_TRACE)
        {
            StringBuilder pos = new StringBuilder();
            StackTraceElement[] sts = Thread.currentThread().getStackTrace();
            if (null != sts)
            {
                if (sts.length > 4)
                {
                    StackTraceElement st = sts[4];
                    pos.append(st.getMethodName()).append(" (").append(st.getFileName()).append(":")
                            .append(st.getLineNumber()).append(") ");
                }
            }
            if (pos.length() <= 0)
            {
                pos.append(DEFAULT_POS);
            }
            begin(pos.toString());
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2) public static void end()
    {
        if (FConfig.SYS_TRACE)
        {
            Trace.endSection();
        }
    }
}
