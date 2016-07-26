package com.ll.horsebean.demo.recyclerview.model;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ll.services.view.recyclerview.FBaseAdapter;
import com.ll.services.view.recyclerview.ViewHolder;

import java.util.List;

/**
 * Created by Liujc on 2016/7/26.
 * Email liujiangchuan@hotmail.com
 */
public class RecyclerViewAdapter extends FBaseAdapter
{
    public RecyclerViewAdapter(List list)
    {
        super(list);
    }

    @Override public int getCustomViewType(int position)
    {
        return 0;
    }

    @Override public ViewHolder createCustomViewHolder(ViewGroup parent, int viewType)
    {
        return null;
    }

    @Override public void bindCustomViewHolder(ViewHolder holder, int position)
    {

    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

    }
}
