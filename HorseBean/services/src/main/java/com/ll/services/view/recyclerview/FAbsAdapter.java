package com.ll.services.view.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ll.services.helper.FLog;

/**
 * Created by Liujc on 2016/7/25.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FAbsAdapter<M, VH extends ViewHolder> extends RecyclerView.Adapter<ViewHolder>
{
    public static final int VIEW_TYPE_HEADER = 1;
    public static final int VIEW_TYPE_FOOTER = 2;

    protected View headerView;
    protected View footerView;

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        switch (viewType)
        {
            case VIEW_TYPE_HEADER:
                return new ViewHolder(headerView);
            case VIEW_TYPE_FOOTER:
                return new ViewHolder(footerView);
            default:
                return createCustomViewHolder(parent, viewType);
        }
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position)
    {
        switch (holder.getItemViewType())
        {
            case VIEW_TYPE_HEADER:
            case VIEW_TYPE_FOOTER:
                break;
            default:
                bindCustomViewHolder((VH) holder, position);
        }
    }

    @Override public int getItemCount()
    {
        return 0;
    }

    public abstract VH createCustomViewHolder(ViewGroup parent, int viewType);

    public abstract void bindCustomViewHolder(VH holder, int position);

    public void addHeaderView(View headerView)
    {
        if (null == headerView)
        {
            FLog.e("header view is null");
            return;
        }
        this.headerView = headerView;
        notifyDataSetChanged();
    }

    public void removeHeaderView()
    {
        if (null != headerView)
        {
            headerView = null;
            notifyDataSetChanged();
        }
    }

    public void addFooterView(View footerView)
    {
        if (null == footerView)
        {
            FLog.e("header view is null");
            return;
        }
        this.footerView = footerView;
        notifyDataSetChanged();
    }

    public void removeFooterView()
    {
        if (null != footerView)
        {
            footerView = null;
            notifyDataSetChanged();
        }
    }

    public int getHeaderViewCount()
    {
        return null == headerView ? 0 : 1;
    }

    public int getFooterViewCount()
    {
        return null == footerView ? 0 : 1;
    }
}
