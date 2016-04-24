package com.ll.services;

import android.util.Log;

public final class FConfig
{
    //log
    public static boolean FLOG_DEBUG = false;
    public static int FLOG_DEBUG_LEVEL = Log.VERBOSE;
    public static boolean FLOG_OUT_TO_FILE = false;
    public static int FLOG_OUT_TO_FILE_LEVEL = Log.WARN;
    //crash
    public static boolean CRASH_CATCH = false;
    //strict mode
    public static boolean STRICT_MODE = false;
    //leak canary
    public static boolean LEAK_CANARY = false;
}
