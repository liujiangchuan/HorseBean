package com.ll.horsebean.demo.ui;

import android.os.Bundle;
import android.widget.ImageView;

import com.ll.horsebean.R;
import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.services.imageload.FImageLoader;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.BindView;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class GlideActivity extends DemoBaseActivity
{
    @BindView(R.id.iv_glide_center) ImageView mIvGlideCenter;
    @BindView(R.id.iv_glide_top) ImageView mIvGlideTop;
    @BindView(R.id.iv_glide_left) ImageView mIvGlideLeft;
    @BindView(R.id.iv_glide_bottom) ImageView mIvGlideBottom;
    @BindView(R.id.iv_glide_right) ImageView mIvGlideRight;

    @Override protected int getLayoutResource()
    {
        return R.layout.activity_glide;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(""/*getClass().getName()*/);
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {
    }

    @Override protected void loadData()
    {
        String url =
                "http://attachments.gfan.com/attachments2/day_110423/1104232300468b2bcfc3bd7e27.jpg";
        FImageLoader.getInstance().loadResGif(R.drawable.gif, mIvGlideCenter);
        FImageLoader.getInstance().loadImage(url, mIvGlideLeft);
        FImageLoader.getInstance().loadImage(url, mIvGlideRight);
        FImageLoader.getInstance().loadCircleImage(url, mIvGlideTop);
        FImageLoader.getInstance().loadRoundRectImage(url, mIvGlideBottom, 20);
    }
}
