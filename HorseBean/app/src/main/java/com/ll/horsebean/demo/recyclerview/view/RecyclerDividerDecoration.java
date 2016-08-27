package com.ll.horsebean.demo.recyclerview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ll.horsebean.R;
import com.ll.services.helper.FLog;


/**
 * Created by Liujc on 2016/8/27.
 * Email: liujiangchuan@hotmail.com
 */
public class RecyclerDividerDecoration extends RecyclerView.ItemDecoration
{
    private Drawable mHorizontalDivider;
    private Drawable mVerticalDivider;

    public RecyclerDividerDecoration(Context context)
    {
        mHorizontalDivider = context.getResources()
                .getDrawable(R.drawable.divider_horizontal_decoration_recycler);
        mVerticalDivider =
                context.getResources().getDrawable(R.drawable.divider_vertical_decoration_recycler);
    }

    @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state)
    {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    @Override public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent)
    {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, itemPosition, spanCount, childCount))
        {
            outRect.set(0, 0, mVerticalDivider.getIntrinsicWidth(), 0);
        }
        else if (isLastColum(parent, itemPosition, spanCount, childCount))
        {
            outRect.set(0, 0, 0, mHorizontalDivider.getIntrinsicHeight());
        }
        else
        {
            outRect.set(0, 0, mVerticalDivider.getIntrinsicWidth(),
                    mHorizontalDivider.getIntrinsicHeight());
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent)
    {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right =
                    child.getRight() + params.rightMargin + mVerticalDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mHorizontalDivider.getIntrinsicHeight();
            mHorizontalDivider.setBounds(left, top, right, bottom);
            mHorizontalDivider.draw(c);
            FLog.i("drawHorizontal",
                    "left: " + left + ", top: " + top + ", right: " + right + ", bottom: " +
                            bottom);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent)
    {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mVerticalDivider.getIntrinsicWidth();

            mVerticalDivider.setBounds(left, top, right, bottom);
            mVerticalDivider.draw(c);
            FLog.i("drawVertical", "left: " + left + ", top: " + top + ", right: " + right +
                    ", bottom: " +
                    bottom);
        }
    }

    private int getSpanCount(RecyclerView parent)
    {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount, int childCount)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        }
        else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            }
            else
            {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
            {
                return true;
            }
        }
        else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                {
                    return true;
                }
            }
            else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
