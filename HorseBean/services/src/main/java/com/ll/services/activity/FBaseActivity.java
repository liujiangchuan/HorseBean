package com.ll.services.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import com.ll.services.R;
import com.ll.services.helper.FLog;
import com.ll.services.helper.FStrictModeWrapper;
import com.ll.services.view.titlebar.FTitlebar;
import com.ll.services.view.titlebar.IFTitlebar;
import com.ll.services.view.titlebar.onTitlebarClickListener;

import butterknife.ButterKnife;

public abstract class FBaseActivity extends Activity
{
    public static Handler sUIHandler = new Handler(Looper.getMainLooper());

    @Override protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FStrictModeWrapper.init();
        setBase();
        setContentView(savedInstanceState);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.f_titlebar);
        ButterKnife.bind(this);
        initTitlebar();
        init();
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
     *
     * @param savedInstanceState
     */
    protected abstract void setContentView(Bundle savedInstanceState);

    protected abstract void initTitlebar(IFTitlebar titlebar);

    /**
     * invoke after setContentView()
     */
    protected abstract void init();

    protected abstract void reloadData();

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
}
