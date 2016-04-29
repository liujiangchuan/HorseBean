package com.ll.services.view.layoutloader;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public interface IFLayoutLoader
{
    void setLayoutResource(IFLayoutResource ifLayoutResource);

    void showEmpty(onFLayoutLoaderClickListener loadingClickListener);

    void showError(onFLayoutLoaderClickListener loadingClickListener);

    void showLoading();

    void hide();
}
