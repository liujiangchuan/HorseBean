package com.ll.services.view.cycleviewpager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ll.services.R;
import com.ll.services.imageload.FImageLoader;
import com.ll.services.ui.FBaseFragment;
import com.ll.services.view.FViewPager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liujc on 2016/4/27.
 * Email liujiangchuan@hotmail.com
 */
public class FCycleViewPager extends FBaseFragment implements ViewPager.OnPageChangeListener
{
    //view
    private RelativeLayout mFCycleViewpagerContentLayout;
    private FViewPager mFCycleViewpagerContentViewpager;
    private LinearLayout mFCycleViewpagerContentIndicator;
    //
    private onCycleImageClickListener mOnCycleImageClickListener;
    private List<ImageView> mImageViews = new ArrayList<>();
    private ImageView[] mIndicators;
    private boolean mIsCycle;
    private boolean mIsWheel;
    private boolean mIsScrolling;
    private int mCurrentPosition;
    private ViewPagerAdapter mViewPagerAdapter;
    private MyHandler mMyHandler;
    private int mWheelTime = 5000;
    private int WHEEL = 100; // 转动
    private int WHEEL_WAIT = 101; // 等待
    private long mReleaseTime = 0;

    @Override protected int getLayoutResource()
    {
        return R.layout.f_cycle_viewpager_content;
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {
        findViews();
        mMyHandler = new MyHandler(this);
    }

    private void findViews()
    {
        mFCycleViewpagerContentLayout =
                (RelativeLayout) mRootView.findViewById(R.id.f_cycle_viewpager_content_layout);
        mFCycleViewpagerContentViewpager =
                (FViewPager) mRootView.findViewById(R.id.f_cycle_viewpager_content_viewpager);
        mFCycleViewpagerContentIndicator =
                (LinearLayout) mRootView.findViewById(R.id.f_cycle_viewpager_content_indicator);
    }

    //    @Override protected void onInit(Bundle savedInstanceState)
    //    {
    //
    //    }
    public static ImageView getImageView(Context context, String url)
    {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        FImageLoader.getInstance().loadImage(context, url, imageView);
        return imageView;
    }

    public void setData(List<ImageView> views, onCycleImageClickListener listener)
    {
        mOnCycleImageClickListener = listener;
        mImageViews.clear();

        if (views.size() == 0)
        {
            mFCycleViewpagerContentLayout.setVisibility(View.GONE);
            return;
        }

        for (ImageView item : views)
        {
            mImageViews.add(item);
        }

        int ivSize = views.size();

        mIndicators = new ImageView[ivSize];
        if (mIsCycle)
        {
            mIndicators = new ImageView[ivSize - 2];
        }
        mFCycleViewpagerContentIndicator.removeAllViews();
        for (int i = 0; i < mIndicators.length; i++)
        {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 4;
            params.rightMargin = 4;
            mIndicators[i] = imageView;
            mFCycleViewpagerContentIndicator.addView(imageView);
        }

        mViewPagerAdapter = new ViewPagerAdapter();

        // 默认指向第一项，下方viewPager.setCurrentItem将触发重新计算指示器指向
        setIndicator(0);

        mFCycleViewpagerContentViewpager.setOffscreenPageLimit(3);
        mFCycleViewpagerContentViewpager.setOnPageChangeListener(this);
        mFCycleViewpagerContentViewpager.setAdapter(mViewPagerAdapter);
        if (mIsCycle)
        {
            mFCycleViewpagerContentViewpager.setCurrentItem(1);
        }
        else
        {
            mFCycleViewpagerContentViewpager.setCurrentItem(0);
        }
    }

    private void setIndicator(int selectedPosition)
    {
        for (int i = 0; i < mIndicators.length; i++)
        {
            mIndicators[i].setBackgroundResource(R.drawable.icon_point);
        }
        if (mIndicators.length > selectedPosition)
        {
            mIndicators[selectedPosition].setBackgroundResource(R.drawable.icon_point_pre);
        }
    }

    private class ViewPagerAdapter extends PagerAdapter
    {
        @Override public int getCount()
        {
            return mImageViews.size();
        }

        @Override public boolean isViewFromObject(View arg0, Object arg1)
        {
            return arg0 == arg1;
        }

        @Override public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
        }

        @Override public View instantiateItem(ViewGroup container, final int position)
        {
            ImageView v = mImageViews.get(position);
            if (mOnCycleImageClickListener != null)
            {
                v.setOnClickListener(new View.OnClickListener()
                {

                    @Override public void onClick(View v)
                    {
                        mOnCycleImageClickListener.onCycleImageClick(mCurrentPosition);
                    }
                });
            }
            container.addView(v);
            return v;
        }

        @Override public int getItemPosition(Object object)
        {
            return POSITION_NONE;
        }
    }

    private static class MyHandler extends Handler
    {
        private WeakReference<FCycleViewPager> activityWeakReference;

        public MyHandler(FCycleViewPager activity)
        {
            activityWeakReference = new WeakReference<FCycleViewPager>(activity);
        }

        @Override public void handleMessage(Message msg)
        {
            FCycleViewPager activity = activityWeakReference.get();
            if (activity != null)
            {
                super.handleMessage(msg);
                if (msg.what == activity.WHEEL && activity.mImageViews.size() != 0)
                {
                    if (!activity.mIsScrolling)
                    {
                        int max = activity.mImageViews.size() + 1;
                        int position =
                                (activity.mCurrentPosition + 1) % activity.mImageViews.size();
                        activity.mFCycleViewpagerContentViewpager.setCurrentItem(position, true);
                        if (position == max)
                        { // 最后一页时回到第一页
                            activity.mFCycleViewpagerContentViewpager.setCurrentItem(1, false);
                        }
                    }

                    activity.mReleaseTime = System.currentTimeMillis();
                    activity.mMyHandler.removeCallbacks(activity.runnable);
                    activity.mMyHandler.postDelayed(activity.runnable, activity.mWheelTime);
                    return;
                }
                if (msg.what == activity.WHEEL_WAIT && activity.mImageViews.size() != 0)
                {
                    activity.mMyHandler.removeCallbacks(activity.runnable);
                    activity.mMyHandler.postDelayed(activity.runnable, activity.mWheelTime);
                }
            }
        }
    }

    public void setCycle(boolean isCycle)
    {
        this.mIsCycle = isCycle;
    }

    public void setWheel(boolean isWheel)
    {
        this.mIsWheel = isWheel;
        mIsCycle = true;
        if (isWheel)
        {
            mMyHandler.postDelayed(runnable, mWheelTime);
        }
    }

    final Runnable runnable = new Runnable()
    {
        @Override public void run()
        {
            if (getActivity() != null && !getActivity().isFinishing() && mIsWheel)
            {
                long now = System.currentTimeMillis();
                // 检测上一次滑动时间与本次之间是否有触击(手滑动)操作，有的话等待下次轮播
                if (now - mReleaseTime > mWheelTime - 500)
                {
                    mMyHandler.sendEmptyMessage(WHEEL);
                }
                else
                {
                    mMyHandler.sendEmptyMessage(WHEEL_WAIT);
                }
            }
        }
    };

    public void setTime(int time)
    {
        this.mWheelTime = time;
    }

    public void refreshData()
    {
        if (mViewPagerAdapter != null)
        {
            mViewPagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override public void onPageSelected(int position)
    {
        int max = mImageViews.size() - 1;
        int pos = position;
        mCurrentPosition = position;
        if (mIsCycle)
        {
            if (position == 0)
            {
                mCurrentPosition = max - 1;
            }
            else if (position == max)
            {
                mCurrentPosition = 1;
            }
            pos = mCurrentPosition - 1;
        }
        setIndicator(pos);
    }

    @Override public void onPageScrollStateChanged(int state)
    {
        if (state == 1)
        { // viewPager在滚动
            mIsScrolling = true;
            return;
        }
        else if (state == 0)
        {
            mReleaseTime = System.currentTimeMillis();

            mFCycleViewpagerContentViewpager.setCurrentItem(mCurrentPosition, false);

        }
        mIsScrolling = false;
    }
}
