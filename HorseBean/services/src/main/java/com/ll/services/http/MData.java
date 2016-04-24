package com.ll.services.http;

import java.io.Serializable;

/**
 * Created by User on 2016/4/19.
 */
public class MData<T> implements Serializable
{
    //// TODO: 2016/4/20  
    public String id;
    public String type;
    public T dataList;
}
