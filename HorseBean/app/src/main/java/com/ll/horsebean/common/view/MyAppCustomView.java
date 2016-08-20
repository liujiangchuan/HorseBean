package com.ll.horsebean.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Liujc on 2016/8/12.
 * Email liujiangchuan@hotmail.com
 */
public class MyAppCustomView extends View
{
    /**
     * Extract common views by requirement analysis.
     * Add common views to the package: common.view.
     *
     * @param context
     */
    public MyAppCustomView(Context context)
    {
        super(context);
    }

    public MyAppCustomView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MyAppCustomView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }
}
