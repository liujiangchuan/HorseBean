package com.ll.services.helper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ll.services.FApplication;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FSharedPreference
{
    private static final String PATH = "FSharedPreference";
    private SharedPreferences mPreferences;
    private Editor mEditor;

    private static class SingletonClassInstance
    {
        private static final FSharedPreference self = new FSharedPreference();
    }

    public static FSharedPreference getInstance()
    {
        return SingletonClassInstance.self;
    }

    private FSharedPreference()
    {
        mPreferences =
                FApplication.getAppContext().getSharedPreferences(PATH, Activity.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public void putString(String key, String value)
    {
        if (null != mEditor)
        {
            mEditor.putString(key, value);
            mEditor.apply();
        }
        else
        {
            FLog.e("putString mEditor == null");
        }
    }

    public String getString(String key, String defValue)
    {
        String ret = null;
        if (null != mPreferences)
        {
            ret = mPreferences.getString(key, defValue);
        }
        else
        {
            FLog.e("getString mPreferences == null");
        }
        return ret;
    }

    public void putBoolean(String key, boolean value)
    {
        if (null != mEditor)
        {
            mEditor.putBoolean(key, value);
            mEditor.apply();
        }
        else
        {
            FLog.e("putBoolean mEditor == null");
        }
    }

    public boolean getBoolean(String key, boolean defValue)
    {
        boolean ret = false;
        if (null != mPreferences)
        {
            ret = mPreferences.getBoolean(key, defValue);
        }
        else
        {
            FLog.e("getBoolean mPreferences == null");
        }
        return ret;
    }

    public void putFloat(String key, float value)
    {
        if (null != mEditor)
        {
            mEditor.putFloat(key, value);
            mEditor.apply();
        }
        else
        {
            FLog.e("putFloat mEditor == null");
        }
    }

    public float getFloat(String key, float defValue)
    {
        float ret = 0;
        if (null != mPreferences)
        {
            ret = mPreferences.getFloat(key, defValue);
        }
        else
        {
            FLog.e("getFloat mPreferences == null");
        }
        return ret;
    }

    public void putInt(String key, int value)
    {
        if (null != mEditor)
        {
            mEditor.putInt(key, value);
            mEditor.apply();
        }
        else
        {
            FLog.e("putInt mEditor == null");
        }
    }

    public int getInt(String key, int defValue)
    {
        int ret = 0;
        if (null != mPreferences)
        {
            ret = mPreferences.getInt(key, defValue);
        }
        else
        {
            FLog.e("getInt mPreferences == null");
        }
        return ret;
    }

    public void putLong(String key, long value)
    {
        if (null != mEditor)
        {
            mEditor.putLong(key, value);
            mEditor.apply();
        }
        else
        {
            FLog.e("putLong mEditor == null");
        }
    }

    public long getLong(String key, long defValue)
    {
        long ret = 0;
        if (null != mPreferences)
        {
            ret = mPreferences.getLong(key, defValue);
        }
        else
        {
            FLog.e("getLong mPreferences == null");
        }
        return ret;
    }

    public void remove(String key)
    {
        if (null != mEditor)
        {
            mEditor.remove(key);
            mEditor.apply();
        }
    }

    public void clear()
    {
        if (null != mEditor)
        {
            mEditor.clear();
            mEditor.apply();
        }
    }
}
