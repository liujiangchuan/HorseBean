package com.ll.services.view.layoutloader;

import android.view.View;

/**
 * Created by Liujc on 2016/4/26.
 * Email liujiangchuan@hotmail.com
 */
public interface IFLayoutResource
{
    View getEmptyView();

    View getErrorView();

    View getLoadingView();

    int getEmptyResource();

    int getErrorResource();

    int getLoadingResource();

    int getEmptyBtnResource();

    int getErrorBtnResource();

    void setEmptyClickListener(onFLayoutLoaderClickListener fLayoutLoaderClickListener);

    void setErrorClickListener(onFLayoutLoaderClickListener fLayoutLoaderClickListener);
}
