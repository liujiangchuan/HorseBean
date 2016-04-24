package com.ll.services.view.titlebar;

/**
 * Created by Administrator on 2016/3/24.
 */
public interface IFTitlebar
{
    //text
    void setTitleText(int resId);

    //left
    void setLeft1Visible();

    void setLeft1Invisible();

    void setLeft1Resource(int resId);

    //right
    void setRight1Visible();

    void setRight1Invisible();

    void setRight1Text(int resId);
}
