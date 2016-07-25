package com.ll.horsebean.demo.main.activity;

/**
 * Created by Liujc on 2016/5/26.
 * Email liujiangchuan@hotmail.com
 */
public interface IMainActivity
{
    void onActivityCount(int count);

    void onActivityPreExecute();

    void onActivitySuccessData();

    void onActivitySuccessEmpty();

    void onActivityFailed();

    void onActivityAfterExecute();
}
