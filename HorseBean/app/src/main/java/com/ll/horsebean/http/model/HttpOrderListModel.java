package com.ll.horsebean.http.model;

import com.ll.horsebean.http.request.HttpGetOrderListRequest;
import com.ll.services.http.FHttpBaseModel;
import com.ll.services.http.FHttpBaseRequest;
import com.ll.services.http.FHttpManager;
import com.ll.services.http.FHttpModelCallback;

/**
 * Created by Administrator on 2016/4/22.
 */
public class HttpOrderListModel extends FHttpBaseModel
{
    public HttpOrderListModel(FHttpModelCallback ifHttpModelCallback)
    {
        super(ifHttpModelCallback);
    }

    public void sendRequest()
    {
        FHttpBaseRequest getOrderListRequest =
                new HttpGetOrderListRequest.ParamsBuilder().setP("a").build();
        FHttpManager.getInstance().asyncExecute(getOrderListRequest, this);
    }

    @Override public void onPreExecute()
    {
        sendUIPreExecute();
    }

    @Override public void onSuccess(String data)
    {
        //// TODO: 2016/4/22
        sendUISuccessData();
        sendUISuccessEmpty();
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
