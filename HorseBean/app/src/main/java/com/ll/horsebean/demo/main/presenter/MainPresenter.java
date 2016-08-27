package com.ll.horsebean.demo.main.presenter;

import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.horsebean.demo.main.model.MainModel;
import com.ll.horsebean.demo.main.model.bean.ActivityBean;
import com.ll.horsebean.demo.main.view.IMainActivity;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

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

    private Subscription mSubscription;

    public MainPresenter(IMainActivity iMainActivity)
    {
        mIMainActivity = iMainActivity;
        mMainModel = new MainModel();
    }

    public void sendGetActivitesRequest()
    {
        mIMainActivity.onActivityPreExecute();
        mSubscription = mMainModel.reloadActivities().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ActivityBean>>()
                {
                    @Override public void onCompleted()
                    {
                        mIMainActivity.onActivityAfterExecute();
                    }

                    @Override public void onError(Throwable e)
                    {
                        mIMainActivity.onActivityFailed();
                    }

                    @Override public void onNext(List<ActivityBean> list)
                    {
                        if (list.isEmpty())
                        {
                            mIMainActivity.onActivitySuccessEmpty();
                        }
                        else
                        {
                            mIMainActivity.onActivitySuccessData(list);
                            mIMainActivity.onActivityCount(list.size());
                        }
                    }
                });
    }

    public void startActivity(int pos)
    {
        ((DemoBaseActivity) mIMainActivity)
                .startActivity(mMainModel.getActivities().get(pos).intent);
    }

    public void unsubscribe()
    {
        if (null != mSubscription)
        {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }
}
