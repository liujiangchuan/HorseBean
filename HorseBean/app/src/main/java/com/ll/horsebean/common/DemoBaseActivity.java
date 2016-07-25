package com.ll.horsebean.common;

import com.ll.services.ui.FBaseActivity;
import com.ll.services.view.layoutloader.IFLayoutLoader;
import com.ll.services.view.layoutloader.onFLayoutLoaderClickListener;

/**
 * Created by Liujc on 2016/6/18.
 * Email liujiangchuan@hotmail.com
 */
public abstract class DemoBaseActivity extends FBaseActivity
{
    protected void finishWithLoading(IFLayoutLoader ifLayoutLoader,
                                     onFLayoutLoaderClickListener listener)
    {
        if (!ifLayoutLoader.cancelLoading(listener))
        {
            finish();
        }
    }
}
