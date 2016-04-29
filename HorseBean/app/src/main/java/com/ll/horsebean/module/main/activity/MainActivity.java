package com.ll.horsebean.module.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ll.horsebean.R;
import com.ll.horsebean.module.main.PaycodeTimer;
import com.ll.services.helper.FLog;
import com.ll.services.tools.FToast;
import com.ll.services.tools.timer.onFCountdownTimerListener;
import com.ll.services.ui.FBaseActivity;
import com.ll.services.view.layoutloader.onFLayoutLoaderClickListener;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends FBaseActivity
{
    @Bind(R.id.textview) TextView mTextview;
    @Bind(R.id.relativelayout) LinearLayout mRelativeLayout;

    int a = 0;

    @Override protected int getLayoutResource()
    {
        return R.layout.activity_main;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(R.string.app_name);
        titlebar.setRight1Text(R.string.hello_world);
        titlebar.setRight1Visible();
    }

    @Override protected View getLoadingView()
    {
        return mRelativeLayout;
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {
    }

    @Override protected void reloadData()
    {
    }

    @OnClick(R.id.textview) public void onClick()
    {
        a++;
        switch (a % 4)
        {
            case 0:
                getLayoutLoader().showEmpty(new onFLayoutLoaderClickListener()
                {
                    @Override public void onLoadingClick(View v)
                    {
                        FToast.showShort("show Empty");
                    }
                });
                break;
            case 1:
                getLayoutLoader().showError(new onFLayoutLoaderClickListener()
                {
                    @Override public void onLoadingClick(View v)
                    {
                        FToast.showShort("show Error");
                    }
                });
                break;
            case 2:
                getLayoutLoader().showLoading();
                break;
            case 3:
                getLayoutLoader().hide();
                break;
        }
        PaycodeTimer.getInstance().setTimerListener(new onFCountdownTimerListener()
        {
            @Override public void onTimerUpdate(String sec)
            {
                FLog.i("sec: " + sec);
            }

            @Override public void onTimerTimeout()
            {
                FLog.i("onTimeout");
                PaycodeTimer.getInstance().stop();
            }
        }).start();

        startActivity(new Intent(this, MainActivity.class));
    }

    @Override protected void onTitlebarRight1Click(View v)
    {
        super.onTitlebarRight1Click(v);
        FToast.showShort("right");
        PaycodeTimer.getInstance().stop();
    }
}
