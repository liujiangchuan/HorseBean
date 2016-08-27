package com.ll.services.view;

import android.content.Intent;

import com.ll.services.FApplication;
import com.ll.services.helper.FLog;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public final class FWebViewIntent
{
    private static final String STRING_URL = "url";
    private static final String STRING_TITLE = "title";

    public static Intent getFWebViewIntent(String url, String title)
    {
        Intent intent = new Intent(FApplication.getAppContext(), FWebViewActivity.class);
        intent.putExtra(STRING_URL, url);
        intent.putExtra(STRING_TITLE, title);
        FLog.i("Context: " + FApplication.getAppContext().getClass().getName() + ", Url: " + url +
                "title: " + title);
        return intent;
    }

    public static String getUrl(Intent intent)
    {
        return intent.getStringExtra(STRING_URL);
    }

    public static String getTitle(Intent intent)
    {
        return intent.getStringExtra(STRING_TITLE);
    }
}
