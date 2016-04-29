package com.ll.services.view.layoutloader;

import com.ll.services.helper.FLog;

/**
 * Created by Liujc on 2016/4/26.
 * Email liujiangchuan@hotmail.com
 */
public class FLayoutLoaderNoneImpl implements IFLayoutLoader
{
    private String mMsg;

    public FLayoutLoaderNoneImpl(String msg)
    {
        mMsg = msg;
    }


    @Override public void setLayoutResource(IFLayoutResource ifLayoutResource)
    {
        FLog.e(mMsg);
    }

    @Override public void showEmpty(onFLayoutLoaderClickListener loadingClickListener)
    {
        FLog.e(mMsg);
    }

    @Override public void showError(onFLayoutLoaderClickListener loadingClickListener)
    {
        FLog.e(mMsg);
    }

    @Override public void showLoading()
    {
        FLog.e(mMsg);
    }

    @Override public void hide()
    {
        FLog.e(mMsg);
    }
}
