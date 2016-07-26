package com.ll.services.view.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Liujc on 2016/7/26.
 * Email liujiangchuan@hotmail.com
 */
public class FOnRecyclerItemTouchListener implements RecyclerView.OnItemTouchListener
{
    private OnItemClickListener mClickListener;
    private GestureDetector mGestureDetector;

    public FOnRecyclerItemTouchListener(final RecyclerView recyclerView,
                                        final OnItemClickListener listener)
    {
        mClickListener = listener;
        mGestureDetector = new GestureDetector(recyclerView.getContext(),
                new GestureDetector.OnGestureListener()
                {
                    @Override public boolean onDown(MotionEvent e)
                    {
                        return false;
                    }

                    @Override public void onShowPress(MotionEvent e)
                    {

                    }

                    @Override public boolean onSingleTapUp(MotionEvent e)
                    {
                        return true;
                    }

                    @Override
                    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                                            float distanceY)
                    {
                        return false;
                    }

                    @Override public void onLongPress(MotionEvent e)
                    {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (null != childView && null != mClickListener)
                        {
                            mClickListener.onItemLongClick(childView,
                                    recyclerView.getChildAdapterPosition(childView));
                        }
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY)
                    {
                        return false;
                    }
                });
    }

    @Override public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
    {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (null != childView && null != mClickListener && mGestureDetector.onTouchEvent(e))
        {
            mClickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override public void onTouchEvent(RecyclerView rv, MotionEvent e)
    {

    }

    @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept)
    {

    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}
