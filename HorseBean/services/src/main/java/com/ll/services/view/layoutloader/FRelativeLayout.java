package com.ll.services.view.layoutloader;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FRelativeLayout extends RelativeLayout implements IFLayoutLoader
{
    private IFLayoutResource mIFLayoutResource;
    private LayoutParams mLayoutParams;

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

    @Override public void showEmpty(onFLayoutLoaderClickListener fLayoutLoaderClickListener)
    {
        mIFLayoutResource.setEmptyClickListener(fLayoutLoaderClickListener);
        removeView(mIFLayoutResource.getErrorView());
        removeView(mIFLayoutResource.getLoadingView());
        addView(mIFLayoutResource.getEmptyView(), mLayoutParams);
    }

    @Override public void showError(onFLayoutLoaderClickListener fLayoutLoaderClickListener)
    {
        mIFLayoutResource.setErrorClickListener(fLayoutLoaderClickListener);
        removeView(mIFLayoutResource.getEmptyView());
        removeView(mIFLayoutResource.getLoadingView());
        addView(mIFLayoutResource.getErrorView(), mLayoutParams);
    }

    @Override public void showLoading()
    {
        removeView(mIFLayoutResource.getEmptyView());
        removeView(mIFLayoutResource.getErrorView());
        addView(mIFLayoutResource.getLoadingView(), mLayoutParams);
    }

    @Override public void hide()
    {
        removeView(mIFLayoutResource.getEmptyView());
        removeView(mIFLayoutResource.getErrorView());
        removeView(mIFLayoutResource.getLoadingView());
    }
}