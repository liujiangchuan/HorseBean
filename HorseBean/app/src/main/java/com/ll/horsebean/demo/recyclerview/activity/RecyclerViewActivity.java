package com.ll.horsebean.demo.recyclerview.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ll.horsebean.R;
import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.horsebean.demo.recyclerview.model.RecyclerViewAdapter;
import com.ll.services.tools.FToast;
import com.ll.services.view.recyclerview.FOnRecyclerItemTouchListener;
import com.ll.services.view.titlebar.IFTitlebar;

import java.util.ArrayList;
import java.util.List;

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
        mRvView.setLayoutManager(new LinearLayoutManager(this));
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        mRvView.setAdapter(adapter);

        mRvView.addOnItemTouchListener(new FOnRecyclerItemTouchListener(mRvView,
                new FOnRecyclerItemTouchListener.OnItemClickListener()
                {
                    @Override public void onItemClick(View view, int position)
                    {
                        FToast.showShort("pos: " + position);
                    }

                    @Override public void onItemLongClick(View view, int position)
                    {
                        FToast.showShort("pos: " + position);
                    }
                }));
    }

    @Override protected void loadData()
    {

    }
}
