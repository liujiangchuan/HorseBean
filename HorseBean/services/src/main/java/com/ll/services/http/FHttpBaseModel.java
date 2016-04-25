package com.ll.services.http;

import com.ll.services.ui.FBaseActivity;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FHttpBaseModel implements IFHttpRequestCallback
{
    public int mResponseStatus;
    public String mResponseMsg;
    public Exception mResponseException;

    protected FHttpModelCallback mIFHttpModelCallback;

    public FHttpBaseModel(FHttpModelCallback ifHttpModelCallback)
    {
        mIFHttpModelCallback = ifHttpModelCallback;
    }

    public abstract void sendRequest();

    protected void sendUIPreExecute()
    {
        if (null != mIFHttpModelCallback)
        {
            mIFHttpModelCallback.onPreExecute();
        }
    }

    protected void sendUISuccessData()
    {
        if (null != mIFHttpModelCallback)
        {
            FBaseActivity.sUIHandler.post(new Runnable()
            {
                @Override public void run()
                {
                    mIFHttpModelCallback.onSuccessData();
                }
            });
        }
    }

    protected void sendUISuccessEmpty()
    {
        if (null != mIFHttpModelCallback)
        {
            FBaseActivity.sUIHandler.post(new Runnable()
            {
                @Override public void run()
                {
                    mIFHttpModelCallback.onSuccessEmpty();
                }
            });
        }
    }

    protected void sendUIFailure()
    {
        if (null != mIFHttpModelCallback)
        {
            FBaseActivity.sUIHandler.post(new Runnable()
            {
                @Override public void run()
                {
                    mIFHttpModelCallback.onFailure();
                }
            });
        }
    }

    protected void sendUIAfterExecute()
    {
        if (null != mIFHttpModelCallback)
        {
            FBaseActivity.sUIHandler.post(new Runnable()
            {
                @Override public void run()
                {
                    mIFHttpModelCallback.onAfterExecute();
                }
            });
        }
    }
}
