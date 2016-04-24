package com.ll.services.http;

/**
 * Created by User on 2016/4/17.
 */
public interface IFHttpRequestCallback
{
    void onPreExecute();

    void onSuccess(String data);

    void onFailure(final int status, final String msg, final Exception e);

    void onAfterExecute();
}
