package com.ll.services.view.titlebar;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public interface IFTitlebar
{
    //text
    void setTitleText(int resId);

    void setTitleText(String text);

    //left
    void setLeft1Visible();

    void setLeft1Invisible();

    void setLeft1Resource(int resId);

    //right
    void setRight1Visible();

    void setRight1Invisible();

    void setRight1Text(int resId);
}
