package com.ll.horsebean.http;

/**
 * Created by Liujc on 2016/12/11.
 * Email: liujiangchuan@hotmail.com
 */
public interface IHttpCallback<T>
{
    void onSuccess(T bean);

    void onFailure(int status, String msg, Throwable e);

    void onAfterExecute();
}
