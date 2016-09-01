package com.ll.horsebean.demo.http.activity;

/**
 * Created by Liujc on 2016/5/26.
 * Email liujiangchuan@hotmail.com
 */
public interface IHttpActivity
{
    void onHttpPreExecute();

    void onHttpSuccessData(String data);

    void onHttpSuccessEmpty();

    void onHttpFailed();

    void onHttpAfterExecute();
}
