package com.ll.services.view.titlebar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Liujc on 2016/5/31.
 * Email liujiangchuan@hotmail.com
 */
public class FTitlebarBtn extends TextView implements IFTitlebar.IFTitlebarBtn
{
    public FTitlebarBtn(Context context)
    {
        super(context);
    }

    public FTitlebarBtn(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FTitlebarBtn(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override public void setBtnVisible()
    {
        setVisibility(View.VISIBLE);
    }

    @Override public void setBtnInvisible()
    {
        setVisibility(View.INVISIBLE);
    }

    @Override public void setBtnResource(int resId)
    {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        ImageSpan imageSpan = new ImageSpan(getContext(), bitmap);
        SpannableString spanString = new SpannableString("icon");
        spanString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(spanString);
    }

    @Override public void setBtnText(int resId)
    {
        setText(resId);
    }

    @Override public void setBtnTextColor(int color)
    {
        setTextColor(color);
    }
}
