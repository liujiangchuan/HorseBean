package com.ll.horsebean.demo.http.activity;

import android.os.Bundle;

import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.horsebean.http.model.HttpOrderListModel;
import com.ll.services.http.FHttpBaseModel;
import com.ll.services.http.FHttpModelCallback;
import com.ll.services.view.titlebar.IFTitlebar;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class HttpActivity extends DemoBaseActivity
{
    FHttpBaseModel httpOrderListModel;

    @Override protected int getLayoutResource()
    {
        return 0;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {

    }

    @Override protected void onInit(Bundle savedInstanceState)
    {

    }

    @Override protected void loadData()
    {
        FHttpModelCallback fHttpModelCallback = new FHttpModelCallback()
        {
            @Override public void onSuccessData()
            {
                super.onSuccessData();
            }
        };
        httpOrderListModel = new HttpOrderListModel(fHttpModelCallback);
        //        httpOrderListModel.sendRequest();
    }
}
