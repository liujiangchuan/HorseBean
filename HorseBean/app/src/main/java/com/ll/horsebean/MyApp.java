package com.ll.horsebean;

import android.os.AsyncTask;

import com.ll.services.FApplication;
import com.ll.services.FC;
import com.ll.services.helper.FCrashHandler;
import com.ll.services.helper.FLeakCanary;
import com.ll.services.helper.FLog;
import com.ll.services.tools.FSharedPreference;
import com.ll.services.storage.FCoverStorage;
import com.ll.services.util.FAppUtil;


/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class MyApp extends FApplication
{
    private static MyApp instance;

    public static MyApp getAppContext()
    {
        return instance;
    }

    @Override public void onCreate()
    {
        super.onCreate();
        instance = this;

        new AppInitTask().execute();
    }

    private class AppInitTask extends AsyncTask<String, Integer, Integer>
    {
        @Override protected Integer doInBackground(String... params)
        {
            initMainProcess();
            return null;
        }

        private void initMainProcess()
        {
            FLog.i("AppInitTask start.");
            new MyConfig().init();
            FCrashHandler.getInstance().init();
            new FLeakCanary().install(instance);
            FCoverStorage.clear();
            //first start
            if (FAppUtil.isFirstStartApp())
            {
                FSharedPreference.getInstance()
                        .putString(FC.fSharedPreference.F_CLIENT_VERSION, FAppUtil.getVersionName());
            }
            FLog.i("AppInitTask end.");
        }
    }
}
