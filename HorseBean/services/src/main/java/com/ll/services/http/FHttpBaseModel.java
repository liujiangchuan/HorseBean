package com.ll.services.http;

import com.ll.services.helper.FLog;
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

    protected void sendUIPreExecute()
    {
        if (null != mIFHttpModelCallback)
        {
            mIFHttpModelCallback.onPreExecute();
            FLog.i("[sendUIPreExecute]");
        }
        else
        {
            FLog.e("mIFHttpModelCallback is null!");
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
                    FLog.i("[sendUISuccessData]");
                }
            });
        }
        else
        {
            FLog.e("mIFHttpModelCallback is null!");
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
                    FLog.i("[sendUISuccessEmpty]");
                }
            });
        }
        else
        {
            FLog.e("mIFHttpModelCallback is null!");
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
                    FLog.e("[sendUIFailure] mResponseStatus: " + mResponseStatus +
                            "mResponseMsg: " + mResponseMsg);
                    if (null != mResponseException)
                    {
                        FLog.e("[sendUIFailure] mResponseException: " +
                                mResponseException.getMessage());
                    }
                }
            });
        }
        else
        {
            FLog.e("mIFHttpModelCallback is null!");
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
                    FLog.i("[sendUIAfterExecute]");
                }
            });
        }
        else
        {
            FLog.e("mIFHttpModelCallback is null!");
        }
    }
}
