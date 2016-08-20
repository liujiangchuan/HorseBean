package com.ll.horsebean.demo.main.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Liujc on 2016/8/12.
 * Email liujiangchuan@hotmail.com
 */
public class MainCustomView extends View
{
    /**
     * Extract common views by requirement analysis in business module.
     * Add custom views into the view package.
     *
     * @param context
     */
    public MainCustomView(Context context)
    {
        super(context);
    }

    public MainCustomView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MainCustomView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }
}
