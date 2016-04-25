package com.ll.services.http.builder;

import java.util.Map;

import okhttp3.Request;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FHttpRequestBuilder
{
    protected Object mTag;
    protected String mUrl;
    protected Map<String, String> mCommonParams;
    protected Map<String, String> mParams;

    public abstract Request build();

    protected String appendUrlParams(String url, Map<String, String> params)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url + "?");
        if (null != params && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                stringBuilder.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
