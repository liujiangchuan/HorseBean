package com.ll.services.view.pageloading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ll.services.R;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FPageLoadingView implements IFPageLoading
{
    //inflater
    private LayoutInflater mLayoutInflater;
    //
    private View mContentView;
    private ViewGroup mParentView;
    private int mContentViewIndex;
    //show layout
    private View mEmptyLayout;
    private View mErrorLayout;
    private View mLoadingLayout;

    public FPageLoadingView(Context context, View contentView)
    {
        if (null == contentView)
        {
            throw new IllegalArgumentException("param ContentView can NOT be null!");
        }
        mLayoutInflater = LayoutInflater.from(context);
        mContentView = contentView;
        initView();
    }

    private void initView()
    {
        mParentView = (ViewGroup) mContentView.getParent();
        mContentViewIndex = mParentView.indexOfChild(mContentView);
    }

    private View getEmptyLayout()
    {
        if (null == mEmptyLayout)
        {
            mEmptyLayout = mLayoutInflater.inflate(R.layout.f_layout_empty, null);
        }
        return mEmptyLayout;
    }

    private View getErrorLayout()
    {
        if (null == mErrorLayout)
        {
            mErrorLayout = mLayoutInflater.inflate(R.layout.f_layout_error, null);
        }
        return mErrorLayout;
    }

    private View getLoadingLayout()
    {
        if (null == mLoadingLayout)
        {
            mLoadingLayout = mLayoutInflater.inflate(R.layout.f_layout_loading, null);
        }
        return mLoadingLayout;
    }

    @Override public void showEmpty()
    {
        mParentView.removeViewAt(mContentViewIndex);
        mParentView.addView(getEmptyLayout(), mContentViewIndex);
    }

    @Override public void showError()
    {
        mParentView.removeViewAt(mContentViewIndex);
        mParentView.addView(getErrorLayout(), mContentViewIndex);
    }

    @Override public void showLoading()
    {
        mParentView.removeViewAt(mContentViewIndex);
        mParentView.addView(getLoadingLayout(), mContentViewIndex);
    }

    @Override public void hide()
    {
        mParentView.removeViewAt(mContentViewIndex);
        mParentView.addView(mContentView, mContentViewIndex);
    }
}
