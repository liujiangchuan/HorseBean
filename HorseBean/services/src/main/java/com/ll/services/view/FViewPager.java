package com.ll.services.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Liujc on 2016/4/28.
 * Email liujiangchuan@hotmail.com
 */
public class FViewPager extends ViewPager {
    private boolean mScrollable = true;

    public FViewPager(Context context) {
        super(context);
    }

    public FViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean scrollable) {
        mScrollable = scrollable;
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mScrollable) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if (mScrollable) {
            return super.onTouchEvent(arg0);
        } else {
            return false;
        }

    }
}
