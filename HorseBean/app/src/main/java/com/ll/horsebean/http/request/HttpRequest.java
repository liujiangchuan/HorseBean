package com.ll.horsebean.http.request;

import com.ll.services.http.FHttpBaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/20.
 */
public abstract class HttpRequest extends FHttpBaseRequest
{
    private final String COMMON_PARAMS_TOKEN = "token";

    @Override public Map<String, String> getCommonUrlParams()
    {
        Map<String, String> params = new HashMap<>();
        params.put(COMMON_PARAMS_TOKEN, "aa");
        return params;
    }
}
