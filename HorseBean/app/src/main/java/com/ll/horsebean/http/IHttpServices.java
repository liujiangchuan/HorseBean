package com.ll.horsebean.http;

import com.ll.horsebean.http.bean.MovieBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Liujc on 2016/11/20.
 * Email: liujiangchuan@hotmail.com
 */
public interface IHttpServices
{
    @GET("top250")
    Observable<MovieBean> getTopMovie(@Query("start") int start, @Query("count") int count);
}
