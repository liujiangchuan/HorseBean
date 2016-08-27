package com.ll.services.view.basewidget;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Liujc on 2016/7/21.
 * Email: liujiangchuan@hotmail.com
 */
public class FAlertDialog extends AlertDialog
{
    public FAlertDialog(Context context)
    {
        super(context);
    }

    public FAlertDialog(Context context, int theme)
    {
        super(context, theme);
    }

    protected FAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener)
    {
        super(context, cancelable, cancelListener);
    }
}
