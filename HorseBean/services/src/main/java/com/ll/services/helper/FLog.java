package com.ll.services.helper;

import android.util.Log;

import com.ll.services.FConfig;
import com.ll.services.storage.LogStorage;
import com.ll.services.util.FFileUtil;
import com.ll.services.util.FTimeUtil;

public class FLog
{
	public static void v(Object msg)
	{
		v(getFunctionName(), msg);
	}

	public static void v(String tag, Object msg)
	{
		if (null != tag && null != msg)
		{
			if (FConfig.FLOG_DEBUG && FConfig.FLOG_DEBUG_LEVEL <= Log.VERBOSE)
			{
				Log.v(tag, msg.toString());
			}
			if (FConfig.FLOG_OUT_TO_FILE && FConfig.FLOG_OUT_TO_FILE_LEVEL <= Log.VERBOSE)
			{
				writeToLog(tag, msg.toString());
			}
		}
	}

	public static void d(Object msg)
	{
		d(getFunctionName(), msg);
	}

	public static void d(String tag, Object msg)
	{
		if (null != tag && null != msg)
		{
			if (FConfig.FLOG_DEBUG && FConfig.FLOG_DEBUG_LEVEL <= Log.DEBUG)
			{
				Log.d(tag, msg.toString());
			}
			if (FConfig.FLOG_OUT_TO_FILE && FConfig.FLOG_OUT_TO_FILE_LEVEL <= Log.DEBUG)
			{
				writeToLog(tag, msg.toString());
			}
		}
	}

	public static void i(Object msg)
	{
		i(getFunctionName(), msg);
	}

	public static void i(String tag, Object msg)
	{
		if (null != tag && null != msg)
		{
			if (FConfig.FLOG_DEBUG && FConfig.FLOG_DEBUG_LEVEL <= Log.INFO)
			{
				Log.i(tag, msg.toString());
			}
			if (FConfig.FLOG_OUT_TO_FILE && FConfig.FLOG_OUT_TO_FILE_LEVEL <= Log.INFO)
			{
				writeToLog(tag, msg.toString());
			}
		}
	}

	public static void w(Object msg)
	{
		w(getFunctionName(), msg);
	}

	public static void w(String tag, Object msg)
	{
		if (null != tag && null != msg)
		{
			if (FConfig.FLOG_DEBUG && FConfig.FLOG_DEBUG_LEVEL <= Log.WARN)
			{
				Log.w(tag, msg.toString());
			}
			if (FConfig.FLOG_OUT_TO_FILE && FConfig.FLOG_OUT_TO_FILE_LEVEL <= Log.WARN)
			{
				writeToLog(tag, msg.toString());
			}
		}
	}

	public static void e(Object msg)
	{
		e(getFunctionName(), msg);
	}

	public static void e(String tag, Object msg)
	{
		if (null != tag && null != msg)
		{
			if (FConfig.FLOG_DEBUG && FConfig.FLOG_DEBUG_LEVEL <= Log.ERROR)
			{
				Log.e(tag, msg.toString());
			}
			if (FConfig.FLOG_OUT_TO_FILE && FConfig.FLOG_OUT_TO_FILE_LEVEL <= Log.ERROR)
			{
				writeToLog(tag, msg.toString());
			}
		}
	}

	private static String getFunctionName()
	{
		String functionName = null;
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();
		if (null != sts)
		{
			if (sts.length > 4)
			{
				StackTraceElement st = sts[4];
				functionName = "[ " + Thread.currentThread().getName() + ": " + st.getFileName() + ":" + st.getLineNumber() + " " + st.getMethodName() + " ]";
			}
		}
		return functionName;
	}

	private static void writeToLog(String tag, String msg)
	{
		String path = LogStorage.getPath();
		if (null != path)
		{
			String log = "[" + FTimeUtil.getCurrentTime() + "] " + tag + ": " + msg;
			FFileUtil.write2File(path, log, true);
		}
	}
}
