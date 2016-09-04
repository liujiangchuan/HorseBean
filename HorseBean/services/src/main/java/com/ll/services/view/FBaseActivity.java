package com.ll.services.view;

import android.content.DialogInterface;
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
import com.ll.services.helper.FStatisticAgent;
import com.ll.services.helper.FStrictModeWrapper;
import com.ll.services.view.dialog.FLoadingDialog;
import com.ll.services.view.titlebar.FTitlebar;
import com.ll.services.view.titlebar.IFTitlebar;
import com.ll.services.view.titlebar.onFTitlebarClickListener;

import butterknife.ButterKnife;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FBaseActivity extends FragmentActivity
{
    public static Handler sUIHandler = new Handler(Looper.getMainLooper());
    private IFTitlebar mIFTitlebar;
    private FLoadingDialog mFLoadingDialog;

    @Override protected final void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FStrictModeWrapper.init();
        setBase();
        View view = LayoutInflater.from(this).inflate(getLayoutResource(), null);
        setContentView(view);
        ButterKnife.bind(this);
        initTitlebar();
        onInit(savedInstanceState);
        loadData();
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
        FStatisticAgent.onResume(this);
        FLog.i("onResume");
    }

    @Override protected void onPause()
    {
        super.onPause();
        FStatisticAgent.onPause(this);
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
     * Whether need the custom title.
     *
     * @return true if show the title.
     */
    protected boolean isShowTitle()
    {
        return true;
    }

    /**
     * invoke before setContentView()
     */
    protected void setBase()
    {
        if (isShowTitle())
        {
            requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        }
        else
        {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }

    /**
     * invoke setContentView().
     */
    protected abstract int getLayoutResource();

    protected abstract void initTitlebar(IFTitlebar titlebar);

    /**
     * invoke after setContentView()
     *
     * @param savedInstanceState
     */
    protected abstract void onInit(Bundle savedInstanceState);

    protected abstract void loadData();

    //-----------------------------------  titlebar start ------------------------------------
    protected void onTitlebarLeft1Click(View v)
    {
        finish();
        FLog.i("onTitlebarLeft1Click");
    }

    protected void onTitlebarLeft2Click(View v)
    {
        FLog.i("onTitlebarLeft2Click");
    }

    protected void onTitlebarRight1Click(View v)
    {
        FLog.i("onTitlebarRight1Click");
    }

    protected void onTitlebarRight2Click(View v)
    {
        FLog.i("onTitlebarRight1Click");
    }

    private void initTitlebar()
    {
        if (isShowTitle())
        {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.f_titlebar);
            FTitlebar fTitlebar = (FTitlebar) findViewById(R.id.f_titlebar_id);
            if (null != fTitlebar)
            {
                fTitlebar.setOnTitlebarClickListener(new onFTitlebarClickListener()
                {
                    @Override public void onLeft1Click(View v)
                    {
                        onTitlebarLeft1Click(v);
                    }

                    @Override public void onLeft2Click(View v)
                    {
                        onTitlebarLeft2Click(v);
                    }

                    @Override public void onRight1Click(View v)
                    {
                        onTitlebarRight1Click(v);
                    }

                    @Override public void onRight2Click(View v)
                    {
                        onTitlebarRight2Click(v);
                    }
                });
                mIFTitlebar = fTitlebar;
                initTitlebar(mIFTitlebar);
            }
            else
            {
                FLog.e("fTitlebar is null!");
            }
        }
        else
        {
            FLog.w("Do NOT need the custom title.");
        }
    }
    //------------------------------------  titlebar end ------------------------------------

    //-----------------------------------  Loading start ------------------------------------
    public void showLoadingDialog(DialogInterface.OnCancelListener onCancelListener)
    {
        boolean ret = false;
        if (null != mFLoadingDialog)
        {
            ret = mFLoadingDialog.isShowing();
        }
        if (!ret)
        {
            mFLoadingDialog = FLoadingDialog.show(this, false, onCancelListener);
        }
        FLog.i("showLoadingDialog: " + ret);
    }

    public void dismissLoadingDialog()
    {
        if (null != mFLoadingDialog && mFLoadingDialog.isShowing())
        {
            mFLoadingDialog.dismiss();
        }
        FLog.i("dismissLoadingDialog");
    }
    //------------------------------------  Loading end ------------------------------------
}
