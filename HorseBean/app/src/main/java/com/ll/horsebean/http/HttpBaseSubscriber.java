package com.ll.horsebean.http;

import com.ll.horsebean.C;
import com.ll.horsebean.MyApp;
import com.ll.horsebean.R;
import com.ll.horsebean.http.bean.HttpBaseBean;

import rx.Subscriber;

/**
 * Created by Liujc on 2016/12/11.
 * Email: liujiangchuan@hotmail.com
 */
public abstract class HttpBaseSubscriber<T> extends Subscriber<HttpBaseBean<T>>
        implements IHttpCallback<T>
{
    @Override
    public void onNext(HttpBaseBean<T> tHttpBaseBean)
    {
        int status = tHttpBaseBean.status;
        switch (status)
        {
            case C.http.STATUS_OK:
                onSuccess(tHttpBaseBean.data);
                break;
            case C.http.STATUS_ERROR:
                onFailure(status, MyApp.getAppContext().getString(R.string.http_return_error),
                        null);
                break;
            default:
                onFailure(status, MyApp.getAppContext().getString(R.string.http_return_error),
                        null);
        }
    }

    @Override
    public void onError(Throwable e)
    {
        onFailure(C.http.STATUS_EXCEPTION, MyApp.getAppContext().getString(R.string.f_error_data),
                e);
    }

    @Override
    public void onCompleted()
    {
        onAfterExecute();
    }
}
