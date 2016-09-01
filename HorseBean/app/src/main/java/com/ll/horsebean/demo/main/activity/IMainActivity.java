package com.ll.horsebean.demo.main.activity;

import com.ll.horsebean.demo.main.model.bean.ActivityBean;

import java.util.List;

/**
 * Created by Liujc on 2016/5/26.
 * Email liujiangchuan@hotmail.com
 */
public interface IMainActivity
{
    void onActivityCount(int count);

    void onActivityPreExecute();

    void onActivitySuccessData(List<ActivityBean> list);

    void onActivitySuccessEmpty();

    void onActivityFailed();

    void onActivityAfterExecute();
}
