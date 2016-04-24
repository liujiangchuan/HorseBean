package com.ll.services.helper;

import android.os.StrictMode;

import com.ll.services.FConfig;

public class FStrictModeWrapper
{
	public static void init()
	{
		if (FConfig.STRICT_MODE)
		{
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath().build());
		}
	}
}
