package com.ll.horsebean.module.glide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ll.horsebean.R;
import com.ll.services.imageload.FImageLoader;
import com.ll.services.ui.FBaseActivity;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.Bind;
import butterknife.OnClick;

public class GlideActivity extends FBaseActivity
{
    @Bind(R.id.activity_glide_img1) ImageView mActivityGlideImg1;

    @Override protected int getLayoutResource()
    {
        return R.layout.activity_glide;
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
        reloadData();
    }

    @Override protected void reloadData()
    {
        String url = "http://img1.ph.126.net/gGRsUgEni_P9xFrirRs2Ww==/6630801683887325749.jpg";
        Glide.with(this).load(url).placeholder(R.mipmap.ic_launcher).into(mActivityGlideImg1);
    }

    @OnClick(R.id.activity_glide_img1) public void onClick()
    {
        FImageLoader.getInstance()
                .loadResGif(this, R.drawable.f_layout_loading, mActivityGlideImg1);
    }
}
