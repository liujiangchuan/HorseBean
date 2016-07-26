package com.ll.services.view.recyclerview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liujc on 2016/7/25.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FBaseAdapter<M, VH extends ViewHolder> extends FAbsAdapter<M, VH>
{
    private List<M> mDataList;

    public FBaseAdapter(List<M> list)
    {
        mDataList = new ArrayList<>();
        if (null != list)
        {
            mDataList.addAll(list);
        }
    }

    public void notifyDataSetChanged(List<M> list)
    {
        mDataList.clear();
        mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void appendItem(M data)
    {
        mDataList.add(data);
        if (0 == getHeaderViewCount())
        {
            notifyItemInserted(mDataList.size() - 1);
        }
        else
        {
            notifyItemInserted(mDataList.size());
        }
    }

    public void appendList(List<M> list)
    {
        mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void proposeItem(M data)
    {
        mDataList.add(0, data);
        notifyItemInserted(getHeaderViewCount());
    }

    public void proposeList(List<M> list)
    {
        mDataList.addAll(0, list);
        notifyDataSetChanged();
    }

    public void updateItem(M data)
    {
        int index = mDataList.indexOf(data);
        if (index < 0)
        {
            return;
        }
        mDataList.set(index, data);
        if (null == headerView)
        {
            notifyItemChanged(index);
        }
        else
        {
            notifyItemChanged(index + 1);
        }
    }

    public void removeItem(int position)
    {
        if (null == headerView)
        {
            mDataList.remove(position);
        }
        else
        {
            mDataList.remove(position - 1);
        }
    }

    public void removeItem(M data)
    {
        int index = mDataList.indexOf(data);
        if (index < 0)
        {
            return;
        }
        mDataList.remove(data);
        if (null == headerView)
        {
            notifyItemRemoved(index);
        }
        else
        {
            notifyItemRemoved(index + 1);
        }
    }

    public M getItem(int position)
    {
        if (null != headerView && 0 == position ||
                position >= mDataList.size() + getHeaderViewCount())
        {
            return null;
        }
        return null == headerView ? mDataList.get(position) : mDataList.get(position - 1);
    }

    public M getItem(VH holder)
    {
        return getItem(holder.getAdapterPosition());
    }

    public abstract int getCustomViewType(int position);

    @Override public long getItemId(int position)
    {
        return position;
    }

    @Override public int getItemCount()
    {
        return mDataList.size() + getHeaderViewCount() + getFooterViewCount();
    }

    @Override public int getItemViewType(int position)
    {
        if (null != headerView && 0 == position)
        {
            return VIEW_TYPE_HEADER;
        }
        else if (null != footerView && mDataList.size() + getHeaderViewCount() == position)
        {
            return VIEW_TYPE_FOOTER;
        }
        else
        {
            return getCustomViewType(position);
        }
    }
}
