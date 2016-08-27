package com.ll.services.view.basewidget;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by Liujc on 2016/8/18.
 * Email: liujiangchuan@hotmail.com
 */
public class FDialog extends Dialog
{
    public FDialog(Context context)
    {
        super(context);
    }

    public FDialog(Context context, int theme)
    {
        super(context, theme);
    }

    protected FDialog(Context context, boolean cancelable, OnCancelListener cancelListener)
    {
        super(context, cancelable, cancelListener);
    }
}
