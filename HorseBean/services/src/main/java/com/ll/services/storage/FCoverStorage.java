package com.ll.services.storage;

import com.ll.services.FApplication;
import com.ll.services.helper.FLog;
import com.ll.services.util.FFileUtil;

import java.io.File;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FCoverStorage extends FBaseStorage
{
    private final static String DIRECTORY_COVER = "cover";
    private static final String SDCARD_CACHE_COVER_PATH =
            File.separator + DIRECTORY_COVER + File.separator;
    private static final String PHONE_CACHE_COVER_PATH =
            File.separator + DIRECTORY_COVER + File.separator;

    public static String getPath()
    {
        String path = null;
        if (isSDCardExist())
        {
            path = getExternalPath();
        }
        else
        {
            path = getInternalPath();
        }
        buildDir(path);
        return path;
    }

    /**
     * Delete all covers if the storage is limit.
     */
    public static void clear()
    {
        if (isSDCardExist())
        {
            if (isExternalMemoryLimit())
            {
                FLog.w("Delete all external cover cache !!");
                FFileUtil.deleteAllInFolder(getExternalPath());
            }
            else
            {
                FLog.i("External Memory is large enough");
            }
        }
        else
        {
            FLog.w("SDCard is NOT exist!");
        }
        if (isInternalMemoryLimit())
        {
            FLog.w("Delete all internal cover cache !!");
            FFileUtil.deleteAllInFolder(getInternalPath());
        }
    }

    private static String getExternalPath()
    {
        String path = null;
        File file = FApplication.getAppContext().getExternalCacheDir();
        if (null != file)
        {
            path = file.getPath() + SDCARD_CACHE_COVER_PATH;
        }
        return path;
    }

    private static String getInternalPath()
    {
        return FApplication.getAppContext().getCacheDir().getPath() + PHONE_CACHE_COVER_PATH;
    }
}
