package com.ll.services.util;

/**
 * Created by Liujc on 2016/5/13.
 * Email liujiangchuan@hotmail.com
 */
public class FNoDoubleClickUtils
{
    private static long sLastClickTime;
    private final static int SPACE_TIME = 500;

    public static void initLastClickTime()
    {
        sLastClickTime = 0;
    }

    public synchronized static boolean isDoubleClick()
    {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - sLastClickTime > SPACE_TIME)
        {
            isClick2 = false;
        }
        else
        {
            isClick2 = true;
        }
        sLastClickTime = currentTime;
        return isClick2;
    }
}
