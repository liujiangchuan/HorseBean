package com.ll.horsebean.demo.http.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.ll.horsebean.R;
import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.horsebean.demo.http.model.HttpModel;
import com.ll.services.helper.FLog;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class HttpActivity extends DemoBaseActivity implements IHttpActivity
{
    //view
    @Bind(R.id.tv_status) TextView mTvStatus;
    //model
    private HttpModel mHttpModel;

    @Override protected int getLayoutResource()
    {
        return R.layout.activity_http;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(getClass().getName());
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {
        mHttpModel = new HttpModel(this);
    }

    @Override protected void loadData()
    {
        mTvStatus.setText(R.string.empty);
    }

    private void reloadData()
    {
        mHttpModel.sendHttpRequest();
    }

    @Override public void onHttpPreExecute()
    {
        mTvStatus.setText(R.string.loading);
    }

    @Override public void onHttpSuccessData(String data)
    {
        mTvStatus.setText(R.string.success);
    }

    @Override public void onHttpSuccessEmpty()
    {
        mTvStatus.setText(R.string.empty);
    }

    @Override public void onHttpFailed()
    {
        mTvStatus.setText(R.string.failed);
    }

    @Override public void onHttpAfterExecute()
    {
        FLog.i("http after execute");
    }

    @OnClick(R.id.btn_refresh) public void onClick()
    {
        reloadData();
    }
}
