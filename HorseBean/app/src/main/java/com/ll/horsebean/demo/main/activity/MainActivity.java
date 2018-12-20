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
import com.ll.horsebean.demo.main.model.bean.ActivityBean;
import com.ll.horsebean.demo.main.presenter.MainPresenter;
import com.ll.services.FApplication;
import com.ll.services.tools.FToast;
import com.ll.services.view.layoutloader.FLodingRelativeLayout;
import com.ll.services.view.layoutloader.onFLayoutLoaderClickListener;
import com.ll.services.view.titlebar.IFTitlebar;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class MainActivity extends DemoBaseActivity
{
    //view
    @BindView(R.id.lv_activities) ListView mLvActivities;
    @BindView(R.id.rl_parent_layout) FLodingRelativeLayout mRlParentLayout;
    //presenter
    private MainPresenter mPresenter;
    //
    private MainAdapter mMainAdapter;
    private onFLayoutLoaderClickListener mOnFLayoutLoaderClickListener;
    private boolean loadingType;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(R.string.test);
        titlebar.getLeft1().setBtnInvisible();
    }

    @Override
    protected void onInit(Bundle savedInstanceState)
    {
        initData();
        initView();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mPresenter.unsubscribe();
    }

    @Override
    protected void loadData()
    {
        mPresenter.sendGetActivitesRequest();
    }

    private void initData()
    {
        mPresenter = new MainPresenter(new IMainActivity()
        {
            @Override
            public void onShowLoading()
            {
                //choose one of the following functions to show a loading.
                if (loadingType)
                {
                    //No.1 layout loading
                    mRlParentLayout.showLoading();
                }
                else
                {
                    //No.2 dialog loading
                    showLoadingDialog(new DialogInterface.OnCancelListener()
                    {
                        @Override
                        public void onCancel(DialogInterface dialog)
                        {
                            mPresenter.unsubscribe();
                            FToast.showShort("loading dialog is canceled.");
                        }
                    });
                }
            }

            @Override
            public void onDismissLoading()
            {
                //choose one of the following functions to hide the loading.(@ onActivityPreExecute choose)
                if (loadingType)
                {
                    //No.1 layout hide
                    mRlParentLayout.hideLoading();
                }
                else
                {
                    //No.2 dialog hide
                    dismissLoadingDialog();
                }
            }

            @Override
            public void onActivityCount(int count)
            {
                FToast.showShort("Activity count is: " + count);
            }

            @Override
            public void onActivitySuccessData(List<ActivityBean> list)
            {
                mRlParentLayout.showSuccess();
                mMainAdapter.notifyDataSetChanged(list);
            }

            @Override
            public void onActivitySuccessEmpty()
            {
                mRlParentLayout.showEmpty(mOnFLayoutLoaderClickListener);
            }

            @Override
            public void onActivityFailed()
            {
                mRlParentLayout.showError(mOnFLayoutLoaderClickListener);
            }

            @Override
            public void onStartActivity(Intent intent)
            {
                startActivity(intent);
            }
        });
        mMainAdapter = new MainAdapter(null, R.layout.listitem_main);
        mOnFLayoutLoaderClickListener = new onFLayoutLoaderClickListener()
        {
            @Override
            public void onLoadingClick(View v)
            {
                loadData();
            }
        };
        loadingType = true;
    }

    private void initView()
    {
        mLvActivities.setAdapter(mMainAdapter);
        mLvActivities.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                mPresenter.startActivity(position);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        finishWithLoading(mRlParentLayout, mOnFLayoutLoaderClickListener);
    }
}