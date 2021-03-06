package com.ll.services.view.scrollview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FBaseAdapter<T> extends BaseAdapter
{
    protected List<T> mListData;
    protected int mLayoutId;

    public FBaseAdapter(List<T> listData, int layoutId)
    {
        mListData = new ArrayList<>();
        if (null != listData)
        {
            mListData.addAll(listData);
        }
        this.mLayoutId = layoutId;
    }

    public void notifyDataSetChanged(List<T> listData)
    {
        mListData.clear();
        mListData.addAll(listData);
        notifyDataSetChanged();
    }

    @Override public int getCount()
    {
        return mListData == null ? 0 : mListData.size();
    }

    @Override public Object getItem(int position)
    {
        return mListData.get(position);
    }

    @Override public long getItemId(int position)
    {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent)
    {
        FViewHolder holder = FViewHolder.getViewHolder(convertView, mLayoutId);
        fillData(holder, position);
        return holder.getMConvertView();
    }

    protected abstract void fillData(FViewHolder holder, int position);
}
