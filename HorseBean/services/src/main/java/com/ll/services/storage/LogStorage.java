package com.ll.services.storage;

import com.ll.services.FApplication;
import com.ll.services.helper.FLog;
import com.ll.services.util.FFileUtil;

import java.io.File;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class LogStorage extends FBaseStorage
{
    private static final String DIRECTORY_LOG = "log";
    private static final String SDCARD_FILES_LOG_PATH =
            File.separator + DIRECTORY_LOG + File.separator;

    public static String getPath()
    {
        String path = null;
        if (isSDCardExist())
        {
            path = getExternalPath();
        }
        buildDir(path);
        return path;
    }

    public static void clearLog()
    {
        if (isSDCardExist())
        {
            FFileUtil.deleteIfExists(getPath());
        }
        else
        {
            FLog.w("SDCard is NOT exist!");
        }
    }

    private static String getExternalPath()
    {
        String path = null;
        File file = FApplication.getAppContext().getExternalFilesDir(null);
        if (null != file)
        {
            path = file.getPath() + SDCARD_FILES_LOG_PATH;
        }
        return path;
    }
}
