package com.ll.services.http.builder;

import com.ll.services.http.FHttpBaseRequest;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FHttpPostBuilder extends FHttpRequestBuilder
{
    public FHttpPostBuilder(FHttpBaseRequest fHttpRequest)
    {
        mTag = fHttpRequest.getTag();
        mUrl = fHttpRequest.getUrl();
        mCommonParams = fHttpRequest.getCommonUrlParams();
        mParams = fHttpRequest.getParams();
    }

    @Override public Request build()
    {
        String url = appendUrlParams(mUrl, mCommonParams);
        RequestBody requestBody = appendBodyParams(mParams);
        return new Request.Builder().tag(mTag).url(url).post(requestBody).build();
    }

    private RequestBody appendBodyParams(Map<String, String> params)
    {
        FormBody.Builder builder = new FormBody.Builder();
        if (null != params && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                builder.add(key, params.get(key));
            }
        }
        return builder.build();
    }
}
