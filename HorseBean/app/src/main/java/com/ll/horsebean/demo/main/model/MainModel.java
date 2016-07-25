package com.ll.horsebean.demo.main.model;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.ll.horsebean.MyApp;
import com.ll.horsebean.demo.main.activity.IMainActivity;
import com.ll.horsebean.demo.main.model.bean.ActivityBean;
import com.ll.services.helper.FLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liujc on 2016/5/25.
 * Email liujiangchuan@hotmail.com
 */
public class MainModel
{
    //activity inter
    private IMainActivity mIMainActivity;
    //model
    private List<ActivityBean> mList;
    private String[] mExceptActivities = {"com.ll.horsebean.demo.main.activity.MainActivity",
            "com.ll.services.ui.FWebViewActivity"};

    public MainModel(IMainActivity iMainActivity)
    {
        mIMainActivity = iMainActivity;
    }

    /**
     * This is only for making a demo.
     */
    public void sendGetActivitesRequest()
    {
        //pre
        mIMainActivity.onActivityPreExecute();
        //find package
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
                    FLog.i("packageName: " + packageName);
                    setActivityData(packageInfo);
                    //success
                    mIMainActivity.onActivitySuccessData();
                    break;
                }
                if (i == size - 1)
                {
                    //fail
                    mIMainActivity.onActivitySuccessEmpty();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            mIMainActivity.onActivityFailed();
        }
        //finish
        mIMainActivity.onActivityAfterExecute();
    }

    public List<ActivityBean> getActivities()
    {
        return mList;
    }

    private void setActivityData(PackageInfo packageInfo)
    {
        mList = new ArrayList<>();
        ActivityInfo[] activityInfos = packageInfo.activities;
        int activityLength = activityInfos.length;
        mIMainActivity.onActivityCount(activityLength);
        if (activityLength <= 0)
        {
            mIMainActivity.onActivitySuccessEmpty();
        }
        for (int i = 0; i < activityLength; i++)
        {
            ActivityInfo activityInfo = activityInfos[i];
            String activityName = activityInfo.name;
            String pkgName = activityInfo.packageName;
            Intent launchIntent = new Intent();
            launchIntent.setComponent(new ComponentName(pkgName, activityName));

            int exceptLength = mExceptActivities.length;
            boolean isInExceptList = false;
            for (int j = 0; j < exceptLength; j++)
            {
                String exceptActivity = mExceptActivities[j];
                if (exceptActivity.equals(activityName))
                {
                    isInExceptList = true;
                    break;
                }
            }
            if (!isInExceptList)
            {
                //create activity bean
                ActivityBean activityBean = new ActivityBean();
                activityBean.activityName = activityName;
                activityBean.pkgName = pkgName;
                activityBean.intent = launchIntent;
                //add to list
                mList.add(activityBean);
            }
            FLog.i("pkgName: " + pkgName + ", activityName: " + activityName);
        }
    }
}
