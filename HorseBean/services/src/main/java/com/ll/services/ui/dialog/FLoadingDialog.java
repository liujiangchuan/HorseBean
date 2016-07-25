package com.ll.services.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;

import com.ll.services.R;

/**
 * Created by Liujc on 2016/5/30.
 * Email liujiangchuan@hotmail.com
 */
public class FLoadingDialog extends AlertDialog
{
    protected FLoadingDialog(Context context)
    {
        super(context);
    }

    protected FLoadingDialog(Context context, int theme)
    {
        super(context, theme);
    }

    protected FLoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener)
    {
        super(context, cancelable, cancelListener);
    }

    public static FLoadingDialog show(Context context)
    {
        return show(context, false);
    }

    public static FLoadingDialog show(Context context, boolean cancelable)
    {
        return show(context, cancelable, null);
    }

    public static FLoadingDialog show(Context context, boolean cancelable,
                                      OnCancelListener cancelListener)
    {
        FLoadingDialog dialog = new FLoadingDialog(context, cancelable, cancelListener);
        dialog.setOnKeyListener(new OnKeyListener()
        {
            @Override public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    dialog.cancel();
                }
                return false;
            }
        });
        dialog.show();
        return dialog;
    }

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_dialog_loading);
    }
}
