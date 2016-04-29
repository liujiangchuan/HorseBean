package com.ll.services.tools.timer;

/**
 * Created by Liujc on 2016/4/26.
 * Email liujiangchuan@hotmail.com
 */
public interface onFCountdownTimerListener
{
    void onTimerUpdate(String sec);

    void onTimerTimeout();
}
