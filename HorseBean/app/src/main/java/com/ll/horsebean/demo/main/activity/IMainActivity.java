package com.ll.horsebean.demo.main.activity;

import android.content.Intent;

import com.ll.horsebean.common.IDemoBaseActivity;
import com.ll.horsebean.demo.main.model.bean.ActivityBean;

import java.util.List;

/**
 * Created by Liujc on 2016/5/26.
 * Email liujiangchuan@hotmail.com
 */
public interface IMainActivity extends IDemoBaseActivity
{
    void onActivityCount(int count);

    void onActivitySuccessData(List<ActivityBean> list);

    void onActivitySuccessEmpty();

    void onActivityFailed();

    void onStartActivity(Intent intent);
}
