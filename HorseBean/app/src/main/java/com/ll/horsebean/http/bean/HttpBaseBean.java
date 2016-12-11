package com.ll.horsebean.http.bean;

import java.io.Serializable;

/**
 * Created by Liujc on 2016/12/11.
 * Email: liujiangchuan@hotmail.com
 */
public class HttpBaseBean<T> implements Serializable
{
    public int status;
    public String message;
    public T data;
}
