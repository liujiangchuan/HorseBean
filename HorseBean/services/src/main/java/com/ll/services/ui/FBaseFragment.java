package com.ll.services.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ll.services.helper.FLog;

import butterknife.ButterKnife;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FBaseFragment extends Fragment
{
    protected View mRootView;

    @Nullable @Override public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                       Bundle savedInstanceState)
    {
        if (mRootView == null)
        {
            mRootView = inflater.inflate(getLayoutResource(), container, false);
        }
        ButterKnife.bind(this, mRootView);
        ViewGroup parentView = (ViewGroup) mRootView.getParent();
        if (parentView != null)
        {
            parentView.removeView(mRootView);
        }
        onInit(savedInstanceState);
        FLog.i("onCreateView");
        return mRootView;
    }

    protected abstract int getLayoutResource();

    protected abstract void onInit(Bundle savedInstanceState);

    public abstract void reloadData();

    public String getName()
    {
        return FBaseFragment.class.getName();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FLog.i("onCreate");
    }

    @Override public void onStart()
    {
        super.onStart();
        FLog.i("onStart");
    }

    @Override public void onResume()
    {
        super.onResume();
        FLog.i("onResume");
    }

    @Override public void onPause()
    {
        super.onPause();
        FLog.i("onPause");
    }

    @Override public void onStop()
    {
        super.onStop();
        FLog.i("onStop");
    }

    @Override public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
        FLog.i("onDestroyView");
    }

    @Override public void onDestroy()
    {
        super.onDestroy();
        FLog.i("onDestroy");
    }
}
