package com.ll.horsebean.demo;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ll.horsebean.R;
import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.Bind;

/**
 * Created by Liujc on 2016/7/25.
 * Email liujiangchuan@hotmail.com
 */
public class RecyclerViewActivity extends DemoBaseActivity
{
    @Bind(R.id.rv_view) RecyclerView mRvView;

    @Override protected int getLayoutResource()
    {
        return R.layout.activity_recyclerview;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {

    }

    @Override protected void onInit(Bundle savedInstanceState)
    {
    }

    @Override protected void loadData()
    {

    }
}
