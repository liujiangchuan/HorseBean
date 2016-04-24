package com.ll.services.storage;

import android.os.Environment;
import android.os.StatFs;

import com.ll.services.helper.FLog;

import java.io.File;

public class FBaseStorage
{
	//max storage remain
	protected static final long MAX_INTERNAL_STORAGE_REMAIN = 5 * 1024 * 1024;
	protected static final long MAX_EXTERNAL_STORAGE_REMAIN = 20 * 1024 * 1024;

	protected static boolean isSDCardExist()
	{
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

	protected static void buildDir(String path)
	{
		if (null != path)
		{
			File dir = new File(path);
			if (!dir.exists())
			{
				if (!dir.mkdirs())
				{
					FLog.e("build dir failed !!");
				}
			}
		}
	}

	protected static boolean isExternalMemoryLimit()
	{
		long ex_available = getAvailableExternalMemorySize();
		FLog.i("AvailableExternalMemorySize: " + ex_available);
		return ex_available < MAX_EXTERNAL_STORAGE_REMAIN;
	}

	protected static boolean isInternalMemoryLimit()
	{
		long in_available = getAvailableInternalMemorySize();
		FLog.i("getAvailableInternalMemorySize: " + in_available);
		return in_available < MAX_INTERNAL_STORAGE_REMAIN;
	}

	private static long getAvailableExternalMemorySize()
	{
		if (isSDCardExist())
		{
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		}
		else
		{
			return -1;
		}
	}

	private static long getAvailableInternalMemorySize()
	{
		File path = Environment.getDataDirectory();
		FLog.i("path: " + path.getPath());
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}
}
