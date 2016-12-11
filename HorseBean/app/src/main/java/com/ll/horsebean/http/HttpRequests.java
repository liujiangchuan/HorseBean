package com.ll.horsebean.http;

import com.ll.horsebean.C;
import com.ll.horsebean.http.bean.HttpBaseBean;
import com.ll.horsebean.http.bean.MovieBean;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Liujc on 2016/12/10.
 * Email: liujiangchuan@hotmail.com
 */
public class HttpRequests
{
    private static HttpRequests ourInstance = new HttpRequests();
    private IHttpServices mIHttpServices;

    public static HttpRequests getInstance()
    {
        return ourInstance;
    }

    private HttpRequests()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(C.http.FORMAL_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        mIHttpServices = retrofit.create(IHttpServices.class);
    }

    public Observable<HttpBaseBean<MovieBean>> getMovies(int start, int count)
    {
        return mIHttpServices.getTopMovie(start, count).map(new Func1<MovieBean, HttpBaseBean<MovieBean>>()
        {
            @Override
            public HttpBaseBean<MovieBean> call(MovieBean movieBean)
            {
                //// TODO: 2016/12/11 Assume that the return data structure is as follows.
                HttpBaseBean<MovieBean> httpBaseBean = new HttpBaseBean();
                httpBaseBean.status = C.http.STATUS_OK;
                httpBaseBean.message = "OK";
                httpBaseBean.data = movieBean;
                return httpBaseBean;
            }
        }).subscribeOn(Schedulers.io());
    }
}
