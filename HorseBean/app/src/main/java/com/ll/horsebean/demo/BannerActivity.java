package com.ll.horsebean.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ll.horsebean.R;
import com.ll.services.ui.FBaseActivity;
import com.ll.services.ui.FWebViewIntent;
import com.ll.services.view.cycleviewpager.FCycleViewPager;
import com.ll.services.view.cycleviewpager.onCycleImageClickListener;
import com.ll.services.view.titlebar.IFTitlebar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liujc on 2016/5/8.
 * Email liujiangchuan@hotmail.com
 */
public class BannerActivity extends FBaseActivity
{
    FCycleViewPager mActivityMainFragment;
    private String[] imageUrls =
            {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
                    "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
                    "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
                    "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
                    "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};

    @Override protected int getLayoutResource()
    {
        return R.layout.activity_banner;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(getClass().getName());
    }

    @Override protected View getLoadingView()
    {
        return null;
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {
        mActivityMainFragment = (FCycleViewPager) getSupportFragmentManager()
                .findFragmentById(R.id.activity_banner_fragment);
        List<ImageView> views = new ArrayList<>();
        int length = imageUrls.length;
        views.add(FCycleViewPager.getImageView(this, imageUrls[length - 1]));
        for (int i = 0; i < length; i++)
        {
            views.add(FCycleViewPager.getImageView(this, imageUrls[i]));
        }
        // 将第一个ImageView添加进来
        views.add(FCycleViewPager.getImageView(this, imageUrls[0]));

        // 设置循环，在调用setData方法前调用
        mActivityMainFragment.setCycle(true);

        // 在加载数据前设置是否循环
        mActivityMainFragment.setData(views, new onCycleImageClickListener()
        {
            @Override public void onCycleImageClick(int pos)
            {
                startActivity(FWebViewIntent.getFWebViewIntent(imageUrls[pos - 1], "PIC"));
            }
        });
        //设置轮播
        mActivityMainFragment.setWheel(true);

        // 设置轮播时间，默认5000ms
        mActivityMainFragment.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        //        mActivityMainFragment.setIndicatorCenter();
    }

    @Override protected void reloadData()
    {

    }
}
