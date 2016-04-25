package com.ll.services.http;

import com.ll.services.http.builder.FHttpRequestBuilder;

import java.util.Map;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FHttpBaseRequest
{
    public Object getTag()
    {
        return getUrl().hashCode();
    }

    public abstract String getUrl();

    public abstract Map<String, String> getParams();

    public abstract Map<String, String> getCommonUrlParams();

    public abstract FHttpRequestBuilder getRequestBuilder();
}
