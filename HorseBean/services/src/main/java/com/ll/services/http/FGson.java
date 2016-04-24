package com.ll.services.http;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/4/20.
 */
public class FGson
{
    private static Gson sGson = new Gson();

    public static Gson get()
    {
        return sGson;
    }
}
