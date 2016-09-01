package com.ll.horsebean.http.model;

import android.text.TextUtils;

import com.ll.horsebean.http.request.HttpGetTestListRequest;
import com.ll.services.http.FHttpBaseModel;
import com.ll.services.http.FHttpBaseRequest;
import com.ll.services.http.FHttpManager;
import com.ll.services.http.FHttpModelCallback;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class HttpTestListModel extends FHttpBaseModel
{
    public String mTestData;

    public HttpTestListModel(FHttpModelCallback ifHttpModelCallback)
    {
        super(ifHttpModelCallback);
    }

    public void sendRequest()
    {
        FHttpBaseRequest getOrderListRequest =
                new HttpGetTestListRequest.ParamsBuilder().setP("a").build();
        FHttpManager.getInstance().asyncExecute(getOrderListRequest, this);
    }

    @Override public void onPreExecute()
    {
        sendUIPreExecute();
    }

    @Override public void onSuccess(String data)
    {
        //// TODO: 2016/4/22 transform data to bean by Gson.
        if (TextUtils.isEmpty(data))
        {
            sendUISuccessEmpty();
        }
        else
        {
            mTestData = data;
            sendUISuccessData();
        }
    }

    @Override public void onFailure(int status, String msg, Exception e)
    {
        sendUIFailure();
    }

    @Override public void onAfterExecute()
    {
        sendUIAfterExecute();
    }
}
