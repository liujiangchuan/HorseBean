package com.ll.horsebean.http.request;

import android.text.TextUtils;

import com.ll.services.http.builder.FHttpGetBuilder;
import com.ll.services.http.builder.FHttpRequestBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class HttpGetTestListRequest extends HttpRequest
{
    private final String URL = "https://...";
    private final String P = "p";
    private Map<String, String> mParams;

    private HttpGetTestListRequest(ParamsBuilder builder)
    {
        mParams = new HashMap<>();
        String p = builder.p;
        if (!TextUtils.isEmpty(p))
        {
            mParams.put(P, builder.p);
        }
    }

    public static class ParamsBuilder
    {
        private String p;

        public ParamsBuilder setP(String p)
        {
            this.p = p;
            return this;
        }

        public HttpGetTestListRequest build()
        {
            return new HttpGetTestListRequest(this);
        }
    }

    @Override public String getHost()
    {
        return null;
    }

    @Override public String getUrl()
    {
        return URL;
    }

    @Override public Map<String, String> getParams()
    {
        return mParams;
    }

    @Override public FHttpRequestBuilder getRequestBuilder()
    {
        return new FHttpGetBuilder(this);
    }
}