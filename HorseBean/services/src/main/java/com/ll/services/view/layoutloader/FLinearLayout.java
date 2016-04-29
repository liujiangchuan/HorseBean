package com.ll.services.view.layoutloader;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FLinearLayout extends LinearLayout implements IFLayoutLoader
{
    private LayoutParams mLayoutParams;
    private IFLayoutResource mIFLayoutResource;

    public FLinearLayout(Context context)
    {
        this(context, null);
    }

    public FLinearLayout(Context context, AttributeSet attrs)
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
        addView(mIFLayoutResource.getEmptyView(), 0, mLayoutParams);
    }

    @Override public void showError(onFLayoutLoaderClickListener fLayoutLoaderClickListener)
    {
        mIFLayoutResource.setErrorClickListener(fLayoutLoaderClickListener);
        removeView(mIFLayoutResource.getEmptyView());
        removeView(mIFLayoutResource.getLoadingView());
        addView(mIFLayoutResource.getErrorView(), 0, mLayoutParams);
    }

    @Override public void showLoading()
    {
        removeView(mIFLayoutResource.getEmptyView());
        removeView(mIFLayoutResource.getErrorView());
        addView(mIFLayoutResource.getLoadingView(), 0, mLayoutParams);
    }

    @Override public void hide()
    {
        removeView(mIFLayoutResource.getEmptyView());
        removeView(mIFLayoutResource.getErrorView());
        removeView(mIFLayoutResource.getLoadingView());
    }
}