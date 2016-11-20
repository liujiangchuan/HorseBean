package com.ll.horsebean.demo.http.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.ll.horsebean.C;
import com.ll.horsebean.R;
import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.horsebean.demo.http.model.HttpModel;
import com.ll.services.helper.FLog;
import com.ll.services.helper.FStatisticAgent;
import com.ll.services.tools.FToast;
import com.ll.services.tools.rxbus.RxBus;
import com.ll.services.tools.rxbus.RxEventBean;
import com.ll.services.view.titlebar.IFTitlebar;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class HttpActivity extends DemoBaseActivity implements IHttpActivity
{
    //view
    @Bind(R.id.btn_refresh) Button mBtnRefresh;
    @Bind(R.id.tv_status) TextView mTvStatus;
    //model
    private HttpModel mHttpModel;
    private Subscription mSubscription;

    @Override protected int getLayoutResource()
    {
        return R.layout.activity_http;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(getClass().getName());
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {
        mHttpModel = new HttpModel(this);
        mSubscription = RxBus.getInstance().toObservable(RxEventBean.class)
                .subscribe(new Action1<RxEventBean>()
                {
                    @Override public void call(RxEventBean rxEventBean)
                    {
                        switch (rxEventBean.id)
                        {
                            case C.rxevent.id.ID_TEST:
                                FToast.showShort(rxEventBean.name);
                                break;
                        }
                    }
                });
        RxView.clicks(mBtnRefresh).throttleFirst(3, TimeUnit.SECONDS).subscribe(new Action1<Void>()
        {
            @Override public void call(Void aVoid)
            {
                reloadData();
                RxBus.getInstance()
                        .post(new RxEventBean(C.rxevent.id.ID_TEST, C.rxevent.name.NAME_TEST_1));
                FStatisticAgent.onEvent(C.statistic.DEMO_HTTP_REFRESH);
            }
        });
    }

    @Override protected void loadData()
    {
        mTvStatus.setText(R.string.empty);
    }

    private void reloadData()
    {
        mHttpModel.sendHttpRequest();
    }

    @Override public void onHttpPreExecute()
    {
        mTvStatus.setText(R.string.loading);
    }

    @Override public void onHttpSuccessData(String data)
    {
        mTvStatus.setText(R.string.success);
    }

    @Override public void onHttpSuccessEmpty()
    {
        mTvStatus.setText(R.string.empty);
    }

    @Override public void onHttpFailed()
    {
        mTvStatus.setText(R.string.failed);
    }

    @Override public void onHttpAfterExecute()
    {
        FLog.i("http after execute");
    }

    @Override protected void onDestroy()
    {
        super.onDestroy();
        mSubscription.unsubscribe();
    }
}
