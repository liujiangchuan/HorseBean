package com.ll.services.http;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public interface IFHttpRequestCallback
{
    void onPreExecute();

    void onSuccess(String data);

    void onFailure(final int status, final String msg, final Exception e);

    void onAfterExecute();
}
