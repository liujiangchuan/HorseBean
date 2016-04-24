package com.ll.services.util;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

import com.ll.services.FC;
import com.ll.services.FApplication;
import com.ll.services.helper.FSharedPreference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FAppUtil
{
    public static String getAppName()
    {
        try
        {
            PackageManager packageManager = FApplication.getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager
                    .getPackageInfo(FApplication.getAppContext().getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return FApplication.getAppContext().getResources().getString(labelRes);
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String getVersionName()
    {
        try
        {
            PackageManager packageManager = FApplication.getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager
                    .getPackageInfo(FApplication.getAppContext().getPackageName(), 0);
            return packageInfo.versionName;
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void exitApp()
    {
        System.exit(0);
    }

    /**
     * Whether the App is first start by client version name.
     *
     * @return
     */
    public static boolean isFirstStartApp()
    {
        boolean ret = false;
        String clientVersion = getVersionName();
        if (!FSharedPreference.getInstance().getString(FC.fSharedPreference.F_CLIENT_VERSION, "")
                .equals(clientVersion))
        {
            ret = true;
        }
        return ret;
    }

    public static void installAPP(String downloadPath)
    {
        if (null != downloadPath)
        {
            File file = new File(downloadPath);
            Uri packageUri = Uri.fromFile(file);
            Intent i = new Intent(Intent.ACTION_VIEW, packageUri);
            i.setDataAndType(packageUri, "application/vnd.android.package-archive");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            FApplication.getAppContext().startActivity(i);
        }
    }

    public static boolean isAppInstalled(String packageName)
    {
        boolean ret = false;
        final PackageManager packageManager = FApplication.getAppContext().getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (null != pinfo)
        {
            List<String> pName = new ArrayList<String>();
            int size = pinfo.size();
            for (int i = 0; i < size; i++)
            {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
            ret = pName.contains(packageName);
        }
        return ret;
    }

    public static void openAppWithPackageName(String packName)
    {
        PackageManager packageManager = FApplication.getAppContext().getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packName);
        FApplication.getAppContext().startActivity(intent);
    }
}
