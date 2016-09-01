package com.ll.horsebean.demo.main.model;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.ll.horsebean.MyApp;
import com.ll.horsebean.demo.main.model.bean.ActivityBean;
import com.ll.services.helper.FLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Liujc on 2016/5/25.
 * Email liujiangchuan@hotmail.com
 */
public class MainModel
{
    private List<ActivityBean> mList;
    private final String[] EXCEPT_ACTIVITIES = {"com.ll.horsebean.demo.main.activity.MainActivity",
            "com.ll.services.view.FWebViewActivity"};

    public Observable<List<ActivityBean>> reloadActivities()
    {
        return Observable.fromCallable(new Callable<List<ActivityBean>>()
        {
            @Override public List<ActivityBean> call() throws Exception
            {
                initList();
                PackageInfo packageInfo = findPackage();
                if (null != packageInfo)
                {
                    ActivityInfo[] activityInfos = packageInfo.activities;
                    int activityLength = activityInfos.length;
                    for (int i = 0; i < activityLength; i++)
                    {
                        ActivityInfo activityInfo = activityInfos[i];
                        if (!isInExceptList(activityInfo))
                        {
                            ActivityBean activityBean = createActivityBean(activityInfo);
                            mList.add(activityBean);
                        }
                    }
                }
                //wait some seconds for test loading in computation thread.
                Thread.sleep(2000);
                return mList;
            }
        }).subscribeOn(Schedulers.computation());
    }

    public List<ActivityBean> getActivities()
    {
        return mList;
    }

    private void initList()
    {
        if (null == mList)
        {
            mList = new ArrayList<>();
        }
        else
        {
            mList.clear();
        }
    }

    private PackageInfo findPackage()
    {
        try
        {
            PackageManager packageManager = MyApp.getAppContext().getPackageManager();
            List<PackageInfo> packageInfoList =
                    packageManager.getInstalledPackages(packageManager.GET_ACTIVITIES);
            int size = packageInfoList.size();
            for (int i = 0; i < size; i++)
            {
                PackageInfo packageInfo = packageInfoList.get(i);
                String packageName = MyApp.getAppContext().getPackageName();
                if (packageName.equals(packageInfo.packageName))
                {
                    FLog.i("packageName: " + packageInfo);
                    return packageInfo;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isInExceptList(ActivityInfo activityInfo)
    {
        boolean isInExceptList = false;
        String activityName = activityInfo.name;
        int exceptLength = EXCEPT_ACTIVITIES.length;
        for (int j = 0; j < exceptLength; j++)
        {
            String exceptActivity = EXCEPT_ACTIVITIES[j];
            if (exceptActivity.equals(activityName))
            {
                isInExceptList = true;
                break;
            }
        }
        return isInExceptList;
    }

    private ActivityBean createActivityBean(ActivityInfo activityInfo)
    {
        //get data
        String activityName = activityInfo.name;
        String pkgName = activityInfo.packageName;
        Intent launchIntent = new Intent();
        launchIntent.setComponent(new ComponentName(pkgName, activityName));
        //build bean
        ActivityBean activityBean = new ActivityBean();
        activityBean.activityName = activityName;
        activityBean.pkgName = pkgName;
        activityBean.intent = launchIntent;
        return activityBean;
    }
}
