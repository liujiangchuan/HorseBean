package com.ll.services.imageload;

import android.content.Context;
import android.net.Uri;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ll.services.FApplication;
import com.ll.services.R;
import com.ll.services.helper.FLog;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FImageLoader
{
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private FImageLoader()
    {
    }

    private static class GlideControlHolder
    {
        private static FImageLoader instance = new FImageLoader();
    }

    public static FImageLoader getInstance()
    {
        return GlideControlHolder.instance;
    }

    public Uri resourceIdToUri(Context context, int resourceId)
    {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    public void loadImage(Context context, String url, ImageView imageView)
    {
        Glide.with(context).load(url).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade().into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Url=" + url + ", ImageView=" +
                imageView.toString());
    }

    public void loadImage(String url, ImageView imageView)
    {
        loadImage(FApplication.getAppContext(), url, imageView);
    }

    public void loadUriImage(Context context, Uri uri, ImageView imageView)
    {
        Glide.with(context).load(uri).into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Uri=" + uri.toString() +
                ", ImageView=" + imageView.toString());
    }

    public void loadUriImage(Uri uri, ImageView imageView)
    {
        loadUriImage(FApplication.getAppContext(), uri, imageView);
    }

    public void loadResImage(Context context, int resId, ImageView imageView)
    {
        Uri uri = resourceIdToUri(context, resId);
        Glide.with(context).load(uri).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade().into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Uri=" + uri + ", ImageView=" +
                imageView.toString());
    }

    public void loadResImage(int resId, ImageView imageView)
    {
        loadResImage(FApplication.getAppContext(), resId, imageView);
    }

    public void loadLocalImage(Context context, String path, ImageView imageView)
    {
        Glide.with(context).load("file://" + path).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade().into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Path=" + path + ", ImageView=" +
                imageView.toString());
    }

    public void loadLocalImage(String path, ImageView imageView)
    {
        loadLocalImage(FApplication.getAppContext(), path, imageView);
    }

    public void loadCircleImage(Context context, String url, ImageView imageView)
    {
        Glide.with(context).load(url).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade()
                .transform(new FGlideCircleTransform(context)).into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Url=" + url + ", ImageView=" +
                imageView.toString());
    }

    public void loadCircleImage(String url, ImageView imageView)
    {
        loadCircleImage(FApplication.getAppContext(), url, imageView);
    }

    public void loadCircleResImage(Context context, int resId, ImageView imageView)
    {
        Uri uri = resourceIdToUri(context, resId);
        Glide.with(context).load(uri).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade()
                .transform(new FGlideCircleTransform(context)).into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Uri=" + uri + ", ImageView=" +
                imageView.toString());
    }

    public void loadCircleResImage(int resId, ImageView imageView)
    {
        loadCircleResImage(FApplication.getAppContext(), resId, imageView);
    }

    public void loadCircleLocalImage(Context context, String path, ImageView imageView)
    {
        Glide.with(context).load("file://" + path).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade()
                .transform(new FGlideCircleTransform(context)).into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Path=" + path + ", ImageView=" +
                imageView.toString());
    }

    public void loadCircleLocalImage(String path, ImageView imageView)
    {
        loadCircleLocalImage(FApplication.getAppContext(), path, imageView);
    }

    public void loadResGif(Context context, int resId, ImageView imageView)
    {
        Uri uri = resourceIdToUri(context, resId);
        Glide.with(context).load(uri).asGif().placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Uri=" + uri + ", ImageView=" +
                imageView.toString());
    }

    public void loadResGif(int resId, ImageView imageView)
    {
        loadResGif(FApplication.getAppContext(), resId, imageView);
    }

    public void setOnScrollStateChanged(int scollState)
    {
        switch (scollState)
        {
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                Glide.with(FApplication.getAppContext()).pauseRequests();
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                Glide.with(FApplication.getAppContext()).resumeRequests();
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                Glide.with(FApplication.getAppContext()).resumeRequests();
        }
    }

    /**
     *
     * @param context
     * @param url
     * @param imageView
     * @param rx unit dp
     * @param ry unit dp
     */
    public void loadRoundRectImage(Context context, String url, ImageView imageView, int rx, int ry)
    {
        Glide.with(context).load(url).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade()
                .transform(new FGlideRoundRectTransform(context,rx,ry)).into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Url=" + url + ", ImageView=" +
                imageView.toString());
    }

    /**
     *
     * @param url
     * @param imageView
     * @param rx unit dp
     * @param ry unit dp
     */
    public void loadRoundRectImage(String url, ImageView imageView,int rx, int ry)
    {
        loadRoundRectImage(FApplication.getAppContext(), url, imageView, rx, ry);
    }

    /**
     *
     * @param context
     * @param resId
     * @param imageView
     * @param rx unit dp
     * @param ry unit dp
     */
    public void loadRoundRectImageImage(Context context, int resId, ImageView imageView, int rx, int ry)
    {
        Uri uri = resourceIdToUri(context, resId);
        Glide.with(context).load(uri).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade()
                .transform(new FGlideRoundRectTransform(context,rx,ry)).into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Uri=" + uri + ", ImageView=" +
                imageView.toString());
    }

    /**
     *
     * @param resId
     * @param imageView
     * @param rx unit dp
     * @param ry unit dp
     */
    public void loadRoundRectImageImage(int resId, ImageView imageView, int rx, int ry)
    {
        loadRoundRectImageImage(FApplication.getAppContext(), resId, imageView, rx, ry);
    }

    /**
     *
     * @param context
     * @param path
     * @param imageView
     * @param rx unit dp
     * @param ry unit dp
     */
    public void loadRoundRectImageImage(Context context, String path, ImageView imageView,int rx, int ry)
    {
        Glide.with(context).load("file://" + path).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade()
                .transform(new FGlideRoundRectTransform(context,rx,ry)).into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Path=" + path + ", ImageView=" +
                imageView.toString());
    }

    /**
     *
     * @param path
     * @param imageView
     * @param rx unit dp
     * @param ry unit dp
     */
    public void loadRoundRectImageImage(String path, ImageView imageView, int rx, int ry)
    {
        loadRoundRectImageImage(FApplication.getAppContext(), path, imageView,rx, ry);
    }
}
