package com.ll.services.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.ll.services.R;
import com.ll.services.helper.FLog;
import com.ll.services.helper.FStrictModeWrapper;
import com.ll.services.view.pageloading.FPageLoadingView;
import com.ll.services.view.pageloading.IFPageLoading;
import com.ll.services.view.titlebar.FTitlebar;
import com.ll.services.view.titlebar.IFTitlebar;
import com.ll.services.view.titlebar.onTitlebarClickListener;

import butterknife.ButterKnife;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FBaseActivity extends Activity
{
    public static Handler sUIHandler = new Handler(Looper.getMainLooper());
    private IFPageLoading mIFPageLoading;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FStrictModeWrapper.init();
        setBase();
        View view = LayoutInflater.from(this).inflate(getLayoutResource(), null);
        setContentView(view);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.f_titlebar);
        ButterKnife.bind(this);
        initTitlebar();
        initPageLoadingView(view);
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
        initTitlebar(fTitlebar);
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
    }
    //------------------------------------  titlebar end ------------------------------------

    //-----------------------------------  Loading start ------------------------------------
    private void initPageLoadingView(View view)
    {
        mIFPageLoading = new FPageLoadingView(this, view);
    }

    public void showEmptyLayout()
    {
        mIFPageLoading.showEmpty();
    }

    public void showErrorLayout()
    {
        mIFPageLoading.showError();
    }

    public void showLoadingLayout()
    {
        mIFPageLoading.showLoading();
    }

    public void hideLoadingLayout()
    {
        mIFPageLoading.hide();
    }
    //------------------------------------  Loading end ------------------------------------
}
