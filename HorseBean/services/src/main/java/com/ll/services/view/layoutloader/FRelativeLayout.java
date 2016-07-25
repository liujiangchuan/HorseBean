package com.ll.services.view.layoutloader;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.ll.services.helper.FLog;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FRelativeLayout extends RelativeLayout implements IFLayoutLoader
{
    private IFLayoutResource mIFLayoutResource;
    private LayoutParams mLayoutParams;
    private boolean mIsLoading;

    public FRelativeLayout(Context context)
    {
        this(context, null);
    }

    public FRelativeLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mIFLayoutResource = new FLayoutResource(context);
    }

    @Override public void setLayoutResource(IFLayoutResource ifLayoutResource)
    {
        if (null != ifLayoutResource)
        {
            mIFLayoutResource = ifLayoutResource;
        }
    }

    @Override public void showSuccess()
    {
        removeView(mIFLayoutResource.getEmptyView());
        removeView(mIFLayoutResource.getErrorView());
        FLog.i("showSuccess");
    }

    @Override public void showEmpty(onFLayoutLoaderClickListener fLayoutLoaderClickListener)
    {
        mIFLayoutResource.setEmptyClickListener(fLayoutLoaderClickListener);
        removeView(mIFLayoutResource.getEmptyView());
        removeView(mIFLayoutResource.getErrorView());
        addView(mIFLayoutResource.getEmptyView(), mLayoutParams);
        FLog.i("showEmpty");
    }

    @Override public void showError(onFLayoutLoaderClickListener fLayoutLoaderClickListener)
    {
        mIFLayoutResource.setErrorClickListener(fLayoutLoaderClickListener);
        removeView(mIFLayoutResource.getEmptyView());
        removeView(mIFLayoutResource.getErrorView());
        addView(mIFLayoutResource.getErrorView(), mLayoutParams);
        FLog.i("showError");
    }

    @Override public void showLoading()
    {
        mIsLoading = true;
        removeView(mIFLayoutResource.getEmptyView());
        removeView(mIFLayoutResource.getErrorView());
        addView(mIFLayoutResource.getLoadingView(), mLayoutParams);
        FLog.i("showLoading");
    }

    @Override public void hideLoading()
    {
        mIsLoading = false;
        removeView(mIFLayoutResource.getLoadingView());
        FLog.i("hideLoading");
    }

    @Override public boolean cancelLoading(onFLayoutLoaderClickListener fLayoutLoaderClickListener)
    {
        boolean ret = mIsLoading;
        if (mIsLoading)
        {
            hideLoading();
            showEmpty(fLayoutLoaderClickListener);
        }
        FLog.i("cancelLoading: " + ret);
        return ret;
    }
}