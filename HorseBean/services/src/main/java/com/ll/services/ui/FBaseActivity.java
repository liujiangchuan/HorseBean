package com.ll.services.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.ll.services.R;
import com.ll.services.helper.FLog;
import com.ll.services.helper.FStrictModeWrapper;
import com.ll.services.view.layoutloader.FLayoutLoaderNoneImpl;
import com.ll.services.view.layoutloader.IFLayoutLoader;
import com.ll.services.view.titlebar.FTitlebar;
import com.ll.services.view.titlebar.IFTitlebar;
import com.ll.services.view.titlebar.onTitlebarClickListener;

import butterknife.ButterKnife;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FBaseActivity extends FragmentActivity
{
    public static Handler sUIHandler = new Handler(Looper.getMainLooper());
    protected IFTitlebar mIFTitlebar;
    protected IFLayoutLoader mIFLayoutLoader;

    @Override protected final void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FStrictModeWrapper.init();
        setBase();
        View view = LayoutInflater.from(this).inflate(getLayoutResource(), null);
        setContentView(view);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.f_titlebar);
        ButterKnife.bind(this);
        initTitlebar();
        initLoadingView(view);
        onInit(savedInstanceState);
        FLog.i("onCreate");
    }

    @Override protected void onStart()
    {
        super.onStart();
        FLog.i("onStart");
    }

    @Override protected void onResume()
    {
        super.onResume();
        FLog.i("onResume");
    }

    @Override protected void onPause()
    {
        super.onPause();
        FLog.i("onPause");
    }

    @Override protected void onStop()
    {
        super.onStop();
        FLog.i("onStop");
    }

    @Override protected void onDestroy()
    {
        super.onDestroy();
        ButterKnife.unbind(this);
        FLog.i("onDestroy");
    }

    /**
     * invoke before setContentView()
     */
    protected void setBase()
    {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    }

    /**
     * invoke setContentView().
     */
    protected abstract int getLayoutResource();

    protected abstract void initTitlebar(IFTitlebar titlebar);

    /**
     * if loading the whole page, it is not necessary to be override.
     *
     * @return
     */
    protected abstract View getLoadingView();

    /**
     * invoke after setContentView()
     *
     * @param savedInstanceState
     */
    protected abstract void onInit(Bundle savedInstanceState);

    protected abstract void reloadData();

    //-----------------------------------  titlebar start ------------------------------------
    protected void onTitlebarLeft1Click(View v)
    {
        finish();
        FLog.i("onTitlebarLeft1Click");
    }

    protected void onTitlebarRight1Click(View v)
    {
        FLog.i("onTitlebarRight1Click");
    }

    private void initTitlebar()
    {
        FTitlebar fTitlebar = (FTitlebar) findViewById(R.id.f_titlebar_id);
        fTitlebar.setOnTitlebarClickListener(new onTitlebarClickListener()
        {
            @Override public void onLeft1Click(View v)
            {
                onTitlebarLeft1Click(v);
            }

            @Override public void onRight1Click(View v)
            {
                onTitlebarRight1Click(v);
            }
        });
        mIFTitlebar = fTitlebar;
        initTitlebar(mIFTitlebar);
    }
    //------------------------------------  titlebar end ------------------------------------

    //-----------------------------------  Loading start ------------------------------------
    private void initLoadingView(View view)
    {
        View loadingView = getLoadingView();
        if (null != loadingView)
        {
            if (loadingView instanceof IFLayoutLoader)
            {
                mIFLayoutLoader = (IFLayoutLoader) loadingView;
                FLog.i("attach customer loading view success!");
            }
            else
            {
                mIFLayoutLoader = new FLayoutLoaderNoneImpl(
                        "loading view is not a instance of IFLayoutLoader!");
            }
        }
        else if (view instanceof IFLayoutLoader)
        {
            mIFLayoutLoader = (IFLayoutLoader) view;
            FLog.i("attach root view as loading view success!");
        }
        else
        {
            mIFLayoutLoader =
                    new FLayoutLoaderNoneImpl("root view is not a instance of IFLayoutLoader!");
        }
    }

    protected IFLayoutLoader getLayoutLoader()
    {
        return mIFLayoutLoader;
    }
    //------------------------------------  Loading end ------------------------------------
}
