package com.ll.horsebean.demo.http.activity;

import com.ll.horsebean.common.IDemoBaseActivity;
import com.ll.horsebean.http.bean.MovieBean;

/**
 * Created by Liujc on 2016/5/26.
 * Email liujiangchuan@hotmail.com
 */
public interface IHttpActivity extends IDemoBaseActivity
{
    void onMoviesDataRefresh(MovieBean bean);

    void onMoviesDataFailed();
}
