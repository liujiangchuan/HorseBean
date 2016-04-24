package com.ll.services.http;

import com.ll.services.http.builder.FHttpRequestBuilder;

import java.util.Map;

/**
 * Created by User on 2016/4/15.
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
