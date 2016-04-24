package com.ll.horsebean.module.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ll.horsebean.R;
import com.ll.horsebean.module.http.HttpActivity;
import com.ll.services.activity.FBaseActivity;
import com.ll.services.helper.FToast;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends FBaseActivity
{
    @Bind(R.id.textview) TextView mTextview;

    @Override protected void setContentView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(R.string.app_name);
        titlebar.setRight1Text(R.string.hello_world);
        titlebar.setRight1Visible();
    }

    @Override protected void init()
    {

    }

    @Override protected void reloadData()
    {
    }

    @OnClick(R.id.textview) public void onClick()
    {
        startActivity(new Intent(this, HttpActivity.class));
    }

    @Override protected void onTitlebarRight1Click(View v)
    {
        super.onTitlebarRight1Click(v);
        FToast.showShort("right");
    }
}
