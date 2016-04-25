package com.ll.services.http;

import java.io.Serializable;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class MData<T> implements Serializable
{
    //// TODO: 2016/4/20  
    public String id;
    public String type;
    public T dataList;
}
