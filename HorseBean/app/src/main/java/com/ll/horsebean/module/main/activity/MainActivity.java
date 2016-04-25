package com.ll.horsebean.module.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ll.horsebean.R;
import com.ll.services.helper.FToast;
import com.ll.services.ui.FBaseActivity;
import com.ll.services.ui.FWebViewIntent;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends FBaseActivity
{
    @Bind(R.id.textview) TextView mTextview;

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
        return null;
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {

    }

    @Override protected void reloadData()
    {
    }

    @OnClick(R.id.textview) public void onClick()
    {
        Intent intent = FWebViewIntent.getFWebViewIntent(this, "http://www.baidu.com", "百度");
        startActivity(intent);
    }

    @Override protected void onTitlebarRight1Click(View v)
    {
        super.onTitlebarRight1Click(v);
        FToast.showShort("right");
    }
}
