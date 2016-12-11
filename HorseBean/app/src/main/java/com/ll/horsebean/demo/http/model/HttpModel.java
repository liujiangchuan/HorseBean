package com.ll.horsebean.demo.http.model;

import com.ll.horsebean.http.HttpRequests;
import com.ll.horsebean.http.bean.HttpBaseBean;
import com.ll.horsebean.http.bean.MovieBean;

import rx.Observable;

/**
 * Created by Liujc on 2016/9/1.
 * Email: liujiangchuan@hotmail.com
 */
public class HttpModel
{
    public Observable<HttpBaseBean<MovieBean>> getMovies(int start, int count)
    {
        return HttpRequests.getInstance().getMovies(start, count);
    }
}
