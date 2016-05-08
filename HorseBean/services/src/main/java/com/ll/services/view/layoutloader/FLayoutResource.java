package com.ll.services.view.layoutloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ll.services.FApplication;
import com.ll.services.R;
import com.ll.services.imageload.FImageLoader;

/**
 * Created by Liujc on 2016/4/26.
 * Email liujiangchuan@hotmail.com
 */
public class FLayoutResource implements IFLayoutResource
{
    protected LayoutInflater mLayoutInflater;
    //show layout
    protected View mEmptyLayout;
    protected View mErrorLayout;
    protected View mLoadingLayout;
    //click listener
    private onFLayoutLoaderClickListener mEmptyClickListener;
    private onFLayoutLoaderClickListener mErrorClickListener;

    public FLayoutResource(Context context)
    {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override public View getEmptyView()
    {
        if (null == mEmptyLayout)
        {
            mEmptyLayout = mLayoutInflater.inflate(getEmptyResource(), null);
            View view = mEmptyLayout.findViewById(getEmptyBtnResource());
            if (null != view)
            {
                view.setOnClickListener(new View.OnClickListener()
                {
                    @Override public void onClick(View v)
                    {
                        if (null != mEmptyClickListener)
                        {
                            mEmptyClickListener.onLoadingClick(v);
                        }
                    }
                });
            }
        }
        return mEmptyLayout;
    }

    @Override public View getErrorView()
    {
        if (null == mErrorLayout)
        {
            mErrorLayout = mLayoutInflater.inflate(getErrorResource(), null);
            View view = mErrorLayout.findViewById(getErrorBtnResource());
            if (null != view)
            {
                view.setOnClickListener(new View.OnClickListener()
                {
                    @Override public void onClick(View v)
                    {
                        if (null != mErrorClickListener)
                        {
                            mErrorClickListener.onLoadingClick(v);
                        }
                    }
                });
            }
        }
        return mErrorLayout;
    }

    @Override public View getLoadingView()
    {
        if (null == mLoadingLayout)
        {
            mLoadingLayout = mLayoutInflater.inflate(getLoadingResource(), null);
            FImageLoader.getInstance()
                    .loadResGif(FApplication.getAppContext(), R.drawable.f_layout_loading,
                            (ImageView) mLoadingLayout.findViewById(R.id.f_layout_loading_img));
        }
        return mLoadingLayout;
    }

    @Override public int getEmptyResource()
    {
        return R.layout.f_layout_empty;
    }

    @Override public int getErrorResource()
    {
        return R.layout.f_layout_error;
    }

    @Override public int getLoadingResource()
    {
        return R.layout.f_layout_loading;
    }

    @Override public int getEmptyBtnResource()
    {
        return R.id.f_layout_empty_btn;
    }

    @Override public int getErrorBtnResource()
    {
        return R.id.f_layout_error_btn;
    }

    @Override
    public void setEmptyClickListener(onFLayoutLoaderClickListener fLayoutLoaderClickListener)
    {
        mEmptyClickListener = fLayoutLoaderClickListener;
    }

    @Override
    public void setErrorClickListener(onFLayoutLoaderClickListener fLayoutLoaderClickListener)
    {
        mErrorClickListener = fLayoutLoaderClickListener;
    }
}
