package com.ll.services.ui;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public final class FWebViewIntent
{
    private static final String STRING_URL = "url";
    private static final String STRING_TITLE = "title";

    public static Intent getFWebViewIntent(Context context, String url, String title)
    {
        Intent intent = new Intent(context, FWebViewActivity.class);
        intent.putExtra(STRING_URL, url);
        intent.putExtra(STRING_TITLE, title);
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
