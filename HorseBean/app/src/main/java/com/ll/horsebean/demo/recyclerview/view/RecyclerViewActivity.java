package com.ll.horsebean.demo.recyclerview.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ll.horsebean.R;
import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.horsebean.demo.recyclerview.model.RecyclerViewAdapter;
import com.ll.horsebean.demo.recyclerview.model.RecyclerViewModel;
import com.ll.services.tools.FToast;
import com.ll.services.view.recyclerview.onFRecyclerItemTouchListener;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.Bind;

/**
 * Created by Liujc on 2016/7/25.
 * Email liujiangchuan@hotmail.com
 */
public class RecyclerViewActivity extends DemoBaseActivity
{
    //view
    @Bind(R.id.rv_view) RecyclerView mRvView;
    //model
    private RecyclerViewModel mModel;
    //adapter
    private RecyclerViewAdapter mAdapter;

    @Override protected int getLayoutResource()
    {
        return R.layout.activity_recyclerview;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(getClass().getName());
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {
        mModel = new RecyclerViewModel();
        mModel.reloadData();

        mRvView.setLayoutManager(
                new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new RecyclerViewAdapter(mModel.mBooks);
        mRvView.setAdapter(mAdapter);
        mRvView.addOnItemTouchListener(new onFRecyclerItemTouchListener(mRvView,
                new onFRecyclerItemTouchListener.OnItemClickListener()
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
        mRvView.addItemDecoration(new RecyclerDividerDecoration(this));
    }

    @Override protected void loadData()
    {

    }
}
