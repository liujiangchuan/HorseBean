package com.ll.horsebean.demo.main.presenter;

import com.ll.horsebean.C;
import com.ll.horsebean.demo.main.activity.IMainActivity;
import com.ll.horsebean.demo.main.model.MainModel;
import com.ll.horsebean.demo.main.model.bean.ActivityBean;
import com.ll.services.tools.rxbus.RxBus;
import com.ll.services.tools.rxbus.RxEventBean;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Liujc on 2016/8/26.
 * Email: liujiangchuan@hotmail.com
 */
public class MainPresenter
{
    //view
    private IMainActivity mIMainActivity;
    //model
    private MainModel mMainModel;
    //subscription
    private Subscription mGetActivitySubscription;
    private Subscription mActivityCountSubscription;

    public MainPresenter(IMainActivity iMainActivity)
    {
        mIMainActivity = iMainActivity;
        mMainModel = new MainModel();
        mActivityCountSubscription = RxBus.getInstance().toObservable(RxEventBean.class)
                .subscribe(new Action1<RxEventBean>()
                {
                    @Override
                    public void call(RxEventBean rxEventBean)
                    {
                        mIMainActivity.onActivityCount(((int) rxEventBean.data));
                    }
                });
    }

    public void sendGetActivitesRequest()
    {
        mIMainActivity.onShowLoading();
        mGetActivitySubscription =
                mMainModel.reloadActivities().observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<ActivityBean>>()
                        {
                            @Override
                            public void onCompleted()
                            {
                                mIMainActivity.onDismissLoading();
                            }

                            @Override
                            public void onError(Throwable e)
                            {
                                mIMainActivity.onActivityFailed();
                            }

                            @Override
                            public void onNext(List<ActivityBean> list)
                            {
                                if (list.isEmpty())
                                {
                                    mIMainActivity.onActivitySuccessEmpty();
                                }
                                else
                                {
                                    mIMainActivity.onActivitySuccessData(list);
                                    //// TODO: 2016/12/11 This is only to show how RxBus works.
                                    RxBus.getInstance()
                                            .post(new RxEventBean(C.rxevent.ID_ACTIVITY_COUNT,
                                                    list.size()));
                                }
                            }
                        });
    }

    public void startActivity(int pos)
    {
        mIMainActivity.onStartActivity(mMainModel.getActivities().get(pos).intent);
    }

    public void unsubscribe()
    {
        if (null != mGetActivitySubscription)
        {
            mGetActivitySubscription.unsubscribe();
            mGetActivitySubscription = null;
        }
        if (null != mActivityCountSubscription)
        {
            mActivityCountSubscription.unsubscribe();
            mActivityCountSubscription = null;
        }
    }
}
