package com.ll.horsebean.demo.main.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ll.horsebean.R;
import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.horsebean.demo.main.model.MainAdapter;
import com.ll.horsebean.demo.main.model.MainModel;
import com.ll.services.tools.FToast;
import com.ll.services.view.layoutloader.FRelativeLayout;
import com.ll.services.view.layoutloader.onFLayoutLoaderClickListener;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.Bind;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class MainActivity extends DemoBaseActivity implements IMainActivity
{
    //view
    @Bind(R.id.lv_activities) ListView mLvActivities;
    @Bind(R.id.rl_parent_layout) FRelativeLayout mRlParentLayout;
    //data
    private MainModel mMainModel;
    private MainAdapter mMainAdapter;
    private onFLayoutLoaderClickListener mOnFLayoutLoaderClickListener;

    @Override protected int getLayoutResource()
    {
        return R.layout.activity_main;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(R.string.test);
        titlebar.getLeft1().setBtnInvisible();
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {
        initData();
        initView();
    }

    @Override protected void loadData()
    {
        mMainModel.sendGetActivitesRequest();
    }

    private void initData()
    {
        mMainModel = new MainModel(this);
        mMainAdapter = new MainAdapter(mMainModel.getActivities(), R.layout.listitem_main);
        mOnFLayoutLoaderClickListener = new onFLayoutLoaderClickListener()
        {
            @Override public void onLoadingClick(View v)
            {
                loadData();
            }
        };
    }

    private void initView()
    {
        mLvActivities.setAdapter(mMainAdapter);
        mLvActivities.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = mMainModel.getActivities().get(position).intent;
                startActivity(intent);
            }
        });
    }

    @Override public void onActivityCount(int count)
    {
        FToast.showLong("Activity count is: " + count);
    }

    @Override public void onActivityPreExecute()
    {
        //choose one of the following functions to show a loading.
        //No.1 layout loading
        mRlParentLayout.showLoading();
        //No.2 dialog loading
        showLoadingDialog(new DialogInterface.OnCancelListener()
        {
            @Override public void onCancel(DialogInterface dialog)
            {
                FToast.showShort("loading dialog is canceled.");
            }
        });
    }

    @Override public void onActivitySuccessData()
    {
        mRlParentLayout.showSuccess();
        mMainAdapter.notifyDataSetChanged(mMainModel.getActivities());
    }

    @Override public void onActivitySuccessEmpty()
    {
        mRlParentLayout.showEmpty(mOnFLayoutLoaderClickListener);
    }

    @Override public void onActivityFailed()
    {
        mRlParentLayout.showError(mOnFLayoutLoaderClickListener);
    }

    @Override public void onActivityAfterExecute()
    {
        //choose one of the following functions to hide the loading.(@ onActivityPreExecute choose)
        //No.1 layout hide
        mRlParentLayout.hideLoading();
        //No.2 dialog hide
        dismissLoadingDialog();
    }

    @Override public void onBackPressed()
    {
        finishWithLoading(mRlParentLayout, mOnFLayoutLoaderClickListener);
    }

    @Override protected void onTitlebarLeft1Click(View v)
    {
        finishWithLoading(mRlParentLayout, mOnFLayoutLoaderClickListener);
    }
}
