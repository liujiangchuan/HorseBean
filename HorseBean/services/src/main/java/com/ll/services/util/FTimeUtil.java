package com.ll.services.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FTimeUtil
{
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_1 = "yyyy-MM-dd";

    /**
     * Get String time by long. {@link FTimeUtil#DEFAULT_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis)
    {
        return getTime(timeInMillis, DEFAULT_FORMAT);
    }

    /**
     * Get String time by long.
     *
     * @param timeInMillis
     * @param format       use format from FDataUtil. {@link FTimeUtil#FORMAT_1}
     * @return
     */
    public static String getTime(long timeInMillis, String format)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(timeInMillis));
    }

    /**
     * Get String current time. {@link FTimeUtil#DEFAULT_FORMAT}
     *
     * @return
     */
    public static String getCurrentTime()
    {
        return getCurrentTime(DEFAULT_FORMAT);
    }

    /**
     * Get String current time.
     *
     * @param format use format from FDataUtil. {@link FTimeUtil#FORMAT_1}
     * @return
     */
    public static String getCurrentTime(String format)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(System.currentTimeMillis()));
    }
}
