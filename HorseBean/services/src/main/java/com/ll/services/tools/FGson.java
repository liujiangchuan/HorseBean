package com.ll.services.tools;

import com.google.gson.Gson;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FGson
{
    private static Gson sGson = new Gson();

    public static Gson get()
    {
        return sGson;
    }
}
