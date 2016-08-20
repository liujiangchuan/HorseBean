package com.ll.services.tools.multiclick;

import com.ll.services.ui.FBaseActivity;

/**
 * Created by Liujc on 2016/5/17.
 * Email liujiangchuan@hotmail.com
 */
public class FMultiClick
{
    //params
    private int mClickTimes;
    private int mMillionSeconds;
    private onFActivateListener mOnFActivateListener;
    //
    private Runnable mRunnable;
    private int mCount;

    public FMultiClick(int clickTimes, int millionSeconds, onFActivateListener listener)
    {
        mClickTimes = clickTimes;
        mMillionSeconds = millionSeconds;
        mOnFActivateListener = listener;
        initRunnable();
    }

    private void initRunnable()
    {
        mRunnable = new Runnable()
        {
            public void run()
            {
                mCount = 0;
            }
        };
    }

    public void onClick()
    {
        if (0 == mCount)
        {
            FBaseActivity.sUIHandler.postDelayed(mRunnable, mMillionSeconds);
        }
        mCount++;
        if (mClickTimes == mCount)
        {
            mOnFActivateListener.onActivate();
        }
    }
}
