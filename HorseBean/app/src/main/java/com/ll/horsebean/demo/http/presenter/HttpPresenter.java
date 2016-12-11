package com.ll.horsebean.demo.http.presenter;

import com.ll.horsebean.demo.http.activity.IHttpActivity;
import com.ll.horsebean.demo.http.model.HttpModel;
import com.ll.horsebean.http.HttpBaseSubscriber;
import com.ll.horsebean.http.bean.MovieBean;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Liujc on 2016/12/11.
 * Email: liujiangchuan@hotmail.com
 */
public class HttpPresenter
{
    private IHttpActivity mIHttpActivity;
    private HttpModel mHttpModel;

    private Subscription mSubscription;

    public HttpPresenter(IHttpActivity iHttpActivity)
    {
        mIHttpActivity = iHttpActivity;
        mHttpModel = new HttpModel();
    }

    public void getMovies(int start, int count)
    {
        mIHttpActivity.onShowLoading();
        mSubscription = mHttpModel.getMovies(start, count).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpBaseSubscriber<MovieBean>()
                {
                    @Override
                    public void onSuccess(MovieBean bean)
                    {
                        mIHttpActivity.onMoviesDataRefresh(bean);
                    }

                    @Override
                    public void onFailure(int status, String msg, Throwable e)
                    {
                        //// TODO: 2016/12/11 Base on status return, dispatch different behaves.
                        mIHttpActivity.onMoviesDataFailed();
                    }

                    @Override
                    public void onAfterExecute()
                    {
                        mIHttpActivity.onDismissLoading();
                    }
                });

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
