package com.ll.services.tools.timer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.ll.services.helper.FLog;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Liujc on 2016/4/26.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FCountdownTimer implements ICountdownTimer
{
    private long mTime;
    private long mPeriod;
    private onFCountdownTimerListener mOnFCountdownTimerListener;
    private Handler mMainThreadHandler;
    private Timer mTimer;
    private TimerTask mTimerTask;

    protected FCountdownTimer()
    {
        mTime = getTime();
        mPeriod = getPeriod();
        mTimer = new Timer();
        mMainThreadHandler = new Handler(Looper.getMainLooper())
        {
            @Override public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                mTime--;
                if (null != mOnFCountdownTimerListener)
                {
                    if (mTime <= 0)
                    {
                        mOnFCountdownTimerListener.onTimerTimeout();
                    }
                    else
                    {
                        mOnFCountdownTimerListener.onTimerUpdate(String.valueOf(mTime));
                    }
                }
                else
                {
                    FLog.e("mOnFCountdownTimerListener is null.");
                }
                FLog.i("mTime: " + mTime);
            }
        };
    }

    public ICountdownTimer setTimerListener(onFCountdownTimerListener listener)
    {
        mOnFCountdownTimerListener = listener;
        return this;
    }

    public String getSec()
    {
        return String.valueOf(mTime);
    }

    @Override public void start()
    {
        if (null == mTimerTask)
        {
            mTime = getTime();
            mTimerTask = new TimerTask()
            {
                @Override public void run()
                {
                    mMainThreadHandler.sendMessage(Message.obtain());
                    FLog.i("mTimerTask running.");
                }
            };
            mTimer.schedule(mTimerTask, 0, mPeriod);
            FLog.i("mTimerTask start.");
        }
        else
        {
            FLog.i("mTimerTask is not null.");
        }
        FLog.i("start");
    }

    @Override public void stop()
    {
        if (null != mTimerTask)
        {
            mTimerTask.cancel();
            mTimerTask = null;
            FLog.i("mTimerTask cancel.");
        }
        else
        {
            FLog.i("mTimerTask is null.");
        }
    }

    public abstract long getTime();

    public abstract long getPeriod();
}
