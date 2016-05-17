package com.ll.horsebean.demo.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ll.horsebean.R;
import com.ll.horsebean.demo.PaycodeTimer;
import com.ll.services.tools.FToast;
import com.ll.services.tools.multiclick.FMultiClick;
import com.ll.services.tools.multiclick.onActivateListener;
import com.ll.services.ui.FBaseActivity;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends FBaseActivity
{
    @Bind(R.id.textview) TextView mTextview;
    @Bind(R.id.relativelayout) LinearLayout mRelativeLayout;

    private FMultiClick mFMultiClick;

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
        mFMultiClick = new FMultiClick(3, 1500, new onActivateListener()
        {
            @Override public void onActivate()
            {
                FToast.showShort("show");
            }
        });
    }

    @Override protected void reloadData()
    {
    }

    @OnClick(R.id.textview) public void onClick()
    {
        mFMultiClick.onClick();
    }

    @Override protected void onTitlebarRight1Click(View v)
    {
        super.onTitlebarRight1Click(v);
        FToast.showShort("right");
        PaycodeTimer.getInstance().stop();
    }
}
