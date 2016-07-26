package com.ll.services.view.recyclerview;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Liujc on 2016/7/25.
 * Email liujiangchuan@hotmail.com
 */
public class ViewHolder extends RecyclerView.ViewHolder
{
    private SparseArray<View> mViews;

    public ViewHolder(View itemView)
    {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public ViewHolder(ViewGroup parent, @LayoutRes int resId)
    {
        super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
        mViews = new SparseArray<>();
    }

    protected <T extends View> T getView(@IdRes int viewId)
    {
        View view = mViews.get(viewId);
        if (null == view)
        {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
