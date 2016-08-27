package com.ll.services.view.basewidget;

import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by Liujc on 2016/7/23.
 * Email liujiangchuan@hotmail.com
 */
public class FPopupWindow extends PopupWindow
{
    @Override public void showAsDropDown(View anchor, int xoff, int yoff, int gravity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            super.showAsDropDown(anchor, xoff, yoff, gravity);
        }
        else
        {
            super.showAsDropDown(anchor, xoff, yoff);
        }
    }
}
