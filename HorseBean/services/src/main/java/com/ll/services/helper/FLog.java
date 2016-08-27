package com.ll.services.helper;

import android.text.TextUtils;
import android.util.Log;

import com.ll.services.FConfig;
import com.ll.services.storage.FLogStorage;
import com.ll.services.util.FTimeUtil;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FLog
{
    //default
    private static final String DEFAULT_TAG = "FLOG";
    private static final String DEFAULT_POS = "Method is NOT found!!";
    //divider
    private static final char TOP_LEFT_CORNER = '╔';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER =
            BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;

    public static void v(Object msg)
    {
        v(null, msg);
    }

    public static void v(String tag, Object msg)
    {
        if (null != msg)
        {
            if (FConfig.FLOG_DEBUG && FConfig.FLOG_DEBUG_LEVEL <= Log.VERBOSE)
            {
                initLog(Log.VERBOSE, tag, msg.toString());
            }
            if (FConfig.FLOG_OUT_TO_FILE && FConfig.FLOG_OUT_TO_FILE_LEVEL <= Log.VERBOSE)
            {
                writeToLog(tag, msg.toString());
            }
        }
    }

    public static void d(Object msg)
    {
        d(null, msg);
    }

    public static void d(String tag, Object msg)
    {
        if (null != msg)
        {
            if (FConfig.FLOG_DEBUG && FConfig.FLOG_DEBUG_LEVEL <= Log.DEBUG)
            {
                initLog(Log.DEBUG, tag, msg.toString());
            }
            if (FConfig.FLOG_OUT_TO_FILE && FConfig.FLOG_OUT_TO_FILE_LEVEL <= Log.DEBUG)
            {
                writeToLog(tag, msg.toString());
            }
        }
    }

    public static void i(Object msg)
    {
        i(null, msg);
    }

    public static void i(String tag, Object msg)
    {
        if (null != msg)
        {
            if (FConfig.FLOG_DEBUG && FConfig.FLOG_DEBUG_LEVEL <= Log.INFO)
            {
                initLog(Log.INFO, tag, msg.toString());
            }
            if (FConfig.FLOG_OUT_TO_FILE && FConfig.FLOG_OUT_TO_FILE_LEVEL <= Log.INFO)
            {
                writeToLog(tag, msg.toString());
            }
        }
    }

    public static void w(Object msg)
    {
        w(null, msg);
    }

    public static void w(String tag, Object msg)
    {
        if (null != msg)
        {
            if (FConfig.FLOG_DEBUG && FConfig.FLOG_DEBUG_LEVEL <= Log.WARN)
            {
                initLog(Log.WARN, tag, msg.toString());
            }
            if (FConfig.FLOG_OUT_TO_FILE && FConfig.FLOG_OUT_TO_FILE_LEVEL <= Log.WARN)
            {
                writeToLog(tag, msg.toString());
            }
        }
    }

    public static void e(Object msg)
    {
        e(null, msg);
    }

    public static void e(String tag, Object msg)
    {
        if (null != msg)
        {
            if (FConfig.FLOG_DEBUG && FConfig.FLOG_DEBUG_LEVEL <= Log.ERROR)
            {
                initLog(Log.ERROR, tag, msg.toString());
            }
            if (FConfig.FLOG_OUT_TO_FILE && FConfig.FLOG_OUT_TO_FILE_LEVEL <= Log.ERROR)
            {
                writeToLog(tag, msg.toString());
            }
        }
    }

    private static void initLog(int level, String tag, String msg)
    {
        String thread = Thread.currentThread().getName();
        StringBuilder pos = new StringBuilder();

        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (null != sts)
        {
            if (sts.length > 5)
            {
                StackTraceElement st = sts[5];
                pos.append(st.getFileName()).append(":").append(st.getLineNumber()).append(" --- ")
                        .append(st.getMethodName());
            }
        }
        if (pos.length() <= 0)
        {
            pos.append(DEFAULT_POS);
        }
        log(level, tag, thread, pos.toString(), msg);
    }

    private static synchronized void log(int level, String tag, String thread, String pos,
                                         String msg)
    {
        logDispatcher(level, tag, TOP_BORDER);
        logDispatcher(level, tag, HORIZONTAL_DOUBLE_LINE + " " + thread);
        logDispatcher(level, tag, MIDDLE_BORDER);
        logDispatcher(level, tag, HORIZONTAL_DOUBLE_LINE + " " + pos);
        logDispatcher(level, tag, MIDDLE_BORDER);
        String[] lines = msg.split(System.getProperty("line.separator"));
        for (String line : lines)
        {
            logDispatcher(level, tag, HORIZONTAL_DOUBLE_LINE + " " + line);
        }
        logDispatcher(level, tag, BOTTOM_BORDER);
    }

    private static void logDispatcher(int level, String customTag, String msg)
    {
        StringBuilder tag = new StringBuilder(DEFAULT_TAG);
        if (!TextUtils.isEmpty(customTag))
        {
            tag.append("_").append(customTag);
        }
        switch (level)
        {
            case Log.VERBOSE:
                Log.v(tag.toString(), msg);
                break;
            case Log.DEBUG:
                Log.d(tag.toString(), msg);
                break;
            case Log.INFO:
                Log.i(tag.toString(), msg);
                break;
            case Log.WARN:
                Log.w(tag.toString(), msg);
                break;
            case Log.ERROR:
                Log.e(tag.toString(), msg);
                break;
        }
    }

    private static void writeToLog(String tag, String msg)
    {
        String path = FLogStorage.getPath();
        if (null != path)
        {
            String log = "[" + FTimeUtil.getCurrentTime() + "] " + tag + ": " + msg;
            //            FFileUtil.write2File(path, log, true);
        }
    }
}
