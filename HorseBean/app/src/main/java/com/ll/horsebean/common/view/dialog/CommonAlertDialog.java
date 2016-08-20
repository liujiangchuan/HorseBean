package com.ll.horsebean.common.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.ll.horsebean.R;

/**
 * Created by Liujc on 2016/7/22.
 * Email: liujiangchuan@hotmail.com
 */
public class CommonAlertDialog extends AlertDialog
{
    protected CommonAlertDialog(Context context)
    {
        super(context);
    }

    protected CommonAlertDialog(Context context, int theme)
    {
        super(context, theme);
    }

    protected CommonAlertDialog(Context context, boolean cancelable,
                                DialogInterface.OnCancelListener cancelListener)
    {
        super(context, cancelable, cancelListener);
    }

    public static class Builder
    {
        private Context mContext;
        private String mTitle;
        private String mMessage;
        private String mEditHint;
        private View mView;
        private String mPositiveButtonText, mNegativeButtonText;
        private DialogInterface.OnClickListener mPositiveButtonListener, mNegativeButtonListener;
        private OnEditClickListener mEditClickListener;

        public Builder(Context context)
        {
            mContext = context;
        }

        public Builder setTitle(String title)
        {
            mTitle = title;
            return this;
        }

        public Builder setMessage(String message)
        {
            mMessage = message;
            return this;
        }

        public Builder setEditHint(String editHint)
        {
            mEditHint = editHint;
            return this;
        }

        public Builder setView(View view)
        {
            mView = view;
            return this;
        }

        public Builder setPositiveButton(String text,
                                         final DialogInterface.OnClickListener listener)
        {
            mPositiveButtonText = text;
            mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(String text, final OnEditClickListener listener)
        {
            mPositiveButtonText = text;
            mEditClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String text,
                                         final DialogInterface.OnClickListener listener)
        {
            mNegativeButtonText = text;
            mNegativeButtonListener = listener;
            return this;
        }

        public AlertDialog create()
        {
            //// TODO: 2016/8/10 build custom view. 
            View view = LayoutInflater.from(mContext).inflate(R.layout.dlg_alert_common, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle(mTitle);
            builder.setMessage(mMessage);
            builder.setView(mView);
            builder.setPositiveButton(mPositiveButtonText, mPositiveButtonListener);
            builder.setNegativeButton(mNegativeButtonText, mNegativeButtonListener);
            return builder.create();
        }
    }

    interface OnEditClickListener
    {
        void onClick(DialogInterface dialog, int which, String text);
    }
}
