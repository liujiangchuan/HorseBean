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

    protected View rootView;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                 Bundle savedInstanceState)
    {
        if (rootView == null)
        {
            rootView = inflater.inflate(getLayoutResource(), container, false);
        }
        ButterKnife.bind(this, rootView);
        ViewGroup parentView = (ViewGroup) rootView.getParent();
        if (parentView != null)
        {
            parentView.removeView(rootView);
        }
        onInit();
        FLog.i("onCreateView");
        return rootView;
    }

    protected abstract int getLayoutResource();

    protected abstract void onInit();

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
