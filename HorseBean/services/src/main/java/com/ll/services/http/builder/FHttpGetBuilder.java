package com.ll.services.http.builder;

import com.ll.services.http.FHttpBaseRequest;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FHttpGetBuilder extends FHttpRequestBuilder
{
    public FHttpGetBuilder(FHttpBaseRequest fHttpRequest)
    {
        mTag = fHttpRequest.getTag();
        mUrl = fHttpRequest.getUrl();
        mCommonParams = fHttpRequest.getCommonUrlParams();
        mParams = fHttpRequest.getParams();
    }

    @Override public Request build()
    {
        Map<String, String> params = new HashMap<>();
        params.putAll(mCommonParams);
        params.putAll(mParams);
        String url = appendUrlParams(mUrl, params);
        return new Request.Builder().tag(mTag).url(url).build();
    }
}
