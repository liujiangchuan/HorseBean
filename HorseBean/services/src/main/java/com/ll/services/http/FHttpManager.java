package com.ll.services.http;

import com.ll.services.FC;
import com.ll.services.helper.FLog;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FHttpManager
{
    public static final int STATUS_ERROR = -1;
    private static FHttpManager ourInstance = new FHttpManager();
    private OkHttpClient mOkHttpClient;

    public static FHttpManager getInstance()
    {
        return ourInstance;
    }

    private FHttpManager()
    {
        mOkHttpClient = new OkHttpClient();
    }

    public void asyncExecute(final FHttpBaseRequest fHttpRequest,
                             final IFHttpRequestCallback fHttpRequestCallback)
    {
        fHttpRequestCallback.onPreExecute();
        try
        {
            mOkHttpClient.newCall(fHttpRequest.getRequestBuilder().build()).enqueue(new Callback()
            {
                @Override public void onFailure(Call call, IOException e)
                {
                    fHttpRequestCallback.onFailure(STATUS_ERROR, e.getMessage(), e);
                    fHttpRequestCallback.onAfterExecute();
                    showRequestLog(call);
                    FLog.e("onFailure e: " + e.getMessage());
                }

                @Override public void onResponse(Call call, Response response)
                {
                    try
                    {
                        //return result
                        String result = response.body().string();
                        FLog.i("onResponse result: " + result);
                        //Get status, msg and data.
                        //If the type of 'data' is confirm JSONObject, we'd better use FHttpResponseBean.
                        //FGson.get().fromJson(result, FHttpResponseBean.class)
                        JSONObject json = new JSONObject(result);
                        int status = json.getInt("status");
                        String msg = json.get("msg").toString();
                        String data = json.get("data").toString();
                        if (FC.fHttp.F_HTTP_RESPONSE_SUCCESS == status)
                        {
                            fHttpRequestCallback.onSuccess(data);
                        }
                        else
                        {
                            fHttpRequestCallback.onFailure(status, msg, null);
                        }
                    }
                    catch (Exception e)
                    {
                        fHttpRequestCallback.onFailure(STATUS_ERROR, e.getMessage(), e);
                    }
                    finally
                    {
                        fHttpRequestCallback.onAfterExecute();
                        showRequestLog(call);
                    }
                }
            });
        }
        catch (Exception e)
        {
            fHttpRequestCallback.onFailure(STATUS_ERROR, e.getMessage(), e);
            fHttpRequestCallback.onAfterExecute();
        }
    }

    public void cancelTag(Object tag)
    {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
    }

    private void showRequestLog(Call call)
    {
        if (null != call)
        {
            Request request = call.request();
            if (null != request)
            {
                Object tag = request.tag();
                HttpUrl url = request.url();
                Headers headers = request.headers();
                RequestBody body = request.body();
                if (null != tag)
                {
                    FLog.i("tag: " + tag.toString());
                }
                else
                {
                    FLog.i("tag is null!");
                }
                if (null != url)
                {
                    FLog.i("url: " + url.toString());
                }
                else
                {
                    FLog.i("url is null!");
                }
                if (null != headers)
                {
                    FLog.i("headers: " + headers.toString());
                }
                else
                {
                    FLog.i("headers is null!");
                }
                if (null != body)
                {
                    FLog.i("body: " + body.toString());
                }
                else
                {
                    FLog.i("body is null!");
                }
            }
        }
        else
        {
            FLog.e("call is null!");
        }
    }
}
