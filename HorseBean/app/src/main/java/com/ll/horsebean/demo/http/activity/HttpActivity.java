package com.ll.horsebean.demo.http.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.ll.horsebean.C;
import com.ll.horsebean.R;
import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.horsebean.demo.http.presenter.HttpPresenter;
import com.ll.horsebean.http.bean.MovieBean;
import com.ll.services.helper.FStatisticAgent;
import com.ll.services.tools.FToast;
import com.ll.services.view.titlebar.IFTitlebar;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class HttpActivity extends DemoBaseActivity
{
    //view
    @Bind(R.id.btn_refresh) Button mBtnRefresh;
    @Bind(R.id.tv_status) TextView mTvStatus;
    //presenter
    private HttpPresenter mHttpPresenter;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_http;
    }

    @Override
    protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(getClass().getName());
    }

    @Override
    protected void onInit(Bundle savedInstanceState)
    {
        mHttpPresenter = new HttpPresenter(new IHttpActivity()
        {
            @Override
            public void onMoviesDataRefresh(MovieBean bean)
            {
                if (null != bean)
                {
                    mTvStatus.setText(bean.title);
                }
                else
                {
                    mTvStatus.setText(R.string.empty);
                }
            }

            @Override
            public void onMoviesDataFailed()
            {
                mTvStatus.setText(R.string.failed);
                FToast.showShort(R.string.failed);
            }

            @Override
            public void onShowLoading()
            {
                showLoadingDialog(new DialogInterface.OnCancelListener()
                {
                    @Override
                    public void onCancel(DialogInterface dialog)
                    {
                        mHttpPresenter.unsubscribe();
                        FToast.showShort(R.string.cancel);
                    }
                });
            }

            @Override
            public void onDismissLoading()
            {
                dismissLoadingDialog();
            }
        });
        RxView.clicks(mBtnRefresh).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>()
        {
            @Override
            public void call(Void aVoid)
            {
                reloadData();
                FStatisticAgent.onEvent(C.statistic.DEMO_HTTP_REFRESH);
            }
        });
    }

    @Override
    protected void loadData()
    {
        mTvStatus.setText(R.string.empty);
        mHttpPresenter.getMovies(1, 5);
    }

    private void reloadData()
    {
        mHttpPresenter.getMovies(1, 5);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mHttpPresenter.unsubscribe();
    }
}
