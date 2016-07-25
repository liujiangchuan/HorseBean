package com.ll.services.view.titlebar;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public interface IFTitlebar
{
    void setTitleText(int resId);

    void setTitleText(String text);

    interface IFTitlebarBtn
    {
        void setBtnVisible();

        void setBtnInvisible();

        void setBtnResource(int resId);

        void setBtnText(int resId);

        void setBtnTextColor(int color);
    }

    IFTitlebarBtn getLeft1();

//    IFTitlebarBtn getLeft2();

    IFTitlebarBtn getRight1();

    IFTitlebarBtn getRight2();
}
