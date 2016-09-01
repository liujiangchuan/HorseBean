package com.ll.horsebean.demo.http.model;

import com.ll.horsebean.demo.http.activity.IHttpActivity;
import com.ll.horsebean.http.model.HttpTestListModel;
import com.ll.services.http.FHttpModelCallback;

/**
 * Created by Liujc on 2016/9/1.
 * Email: liujiangchuan@hotmail.com
 */
public class HttpModel
{
    private IHttpActivity mIHttpActivity;
    private HttpTestListModel mHttpTestListModel;

    public HttpModel(IHttpActivity iHttpActivity)
    {
        mIHttpActivity = iHttpActivity;
    }

    public void sendHttpRequest()
    {
        if (null == mHttpTestListModel)
        {
            mHttpTestListModel = new HttpTestListModel(new FHttpModelCallback()
            {
                @Override public void onPreExecute()
                {
                    if (null != mIHttpActivity)
                    {
                        mIHttpActivity.onHttpPreExecute();
                    }
                }

                @Override public void onSuccessData()
                {
                    if (null != mIHttpActivity)
                    {
                        mIHttpActivity.onHttpSuccessData(mHttpTestListModel.mTestData);
                    }
                }

                @Override public void onSuccessEmpty()
                {
                    if (null != mIHttpActivity)
                    {
                        mIHttpActivity.onHttpSuccessEmpty();
                    }
                }

                @Override public void onFailure()
                {
                    if (null != mIHttpActivity)
                    {
                        mIHttpActivity.onHttpFailed();
                    }
                }

                @Override public void onAfterExecute()
                {
                    if (null != mIHttpActivity)
                    {
                        mIHttpActivity.onHttpAfterExecute();
                    }
                }
            });
        }
        mHttpTestListModel.sendRequest();
    }
}
