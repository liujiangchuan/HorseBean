package com.ll.services.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.ll.services.FApplication;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class ViewHolder
{
    private SparseArray mViews;
    private View mConvertView;

    public ViewHolder(int layoutId)
    {
        mViews = new SparseArray();
        mConvertView = LayoutInflater.from(FApplication.getAppContext()).inflate(layoutId, null);
        mConvertView.setTag(this);
    }

    public static ViewHolder getViewHolder(View convertView, int layoutId)
    {
        if (convertView == null)
        {
            return new ViewHolder(layoutId);
        }
        return (ViewHolder) convertView.getTag();
    }

    public <T extends View> T getView(int viewId)
    {
        View item = (View) mViews.get(viewId);
        if (item == null)
        {
            item = mConvertView.findViewById(viewId);
            mViews.put(viewId, item);
        }
        return (T) item;
    }

    public View getMConvertView()
    {
        return mConvertView;
    }
}