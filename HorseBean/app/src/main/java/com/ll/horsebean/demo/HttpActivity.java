package com.ll.horsebean.demo;

import android.app.Activity;
import android.os.Bundle;

import com.ll.horsebean.http.model.HttpOrderListModel;
import com.ll.services.http.FHttpBaseModel;
import com.ll.services.http.FHttpModelCallback;

/**
 * Created by Administrator on 2016/4/18.
 */
public class HttpActivity extends Activity
{
    FHttpBaseModel httpOrderListModel;

    @Override protected void onCreate(Bundle arg0)
    {
        super.onCreate(arg0);

        FHttpModelCallback fHttpModelCallback = new FHttpModelCallback()
        {
            @Override public void onSuccessData()
            {
                super.onSuccessData();
                httpOrderListModel.toString();
            }
        };
        httpOrderListModel = new HttpOrderListModel(fHttpModelCallback);
        httpOrderListModel.sendRequest();
    }
}
