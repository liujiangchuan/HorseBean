package com.ll.horsebean.module.main;

import com.ll.services.tools.timer.FCountdownTimer;

/**
 * Created by Liujc on 2016/4/29.
 * Email liujiangchuan@hotmail.com
 */
public class PaycodeTimer extends FCountdownTimer
{
    private static PaycodeTimer ourInstance = new PaycodeTimer();

    public static PaycodeTimer getInstance()
    {
        return ourInstance;
    }

    private PaycodeTimer()
    {
    }

    @Override public long getTime()
    {
        return 60;
    }

    @Override public long getPeriod()
    {
        return 1000;
    }
}
