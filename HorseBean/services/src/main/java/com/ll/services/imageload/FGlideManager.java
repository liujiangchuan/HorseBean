package com.ll.services.imageload;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ll.services.R;
import com.ll.services.helper.FLog;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FGlideManager
{
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private FGlideManager()
    {
    }

    private static class GlideControlHolder
    {
        private static FGlideManager instance = new FGlideManager();
    }

    public static FGlideManager getInstance()
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
                .error(R.color.f_transparent_1a).crossFade()
                .transform(new FGlideCircleTransform(context)).into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Url=" + url + ", ImageView=" +
                imageView.toString());
    }

    public void loadResImage(Context context, int resId, ImageView imageView)
    {
        Uri uri = resourceIdToUri(context, resId);
        Glide.with(context).load(uri).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade().into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Uri=" + uri + ", ImageView=" +
                imageView.toString());
    }

    public void loadLocalImage(Context context, String path, ImageView imageView)
    {
        Glide.with(context).load("file://" + path).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade().into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Path=" + path + ", ImageView=" +
                imageView.toString());
    }

    public void loadCircleImage(Context context, String url, ImageView imageView)
    {
        Glide.with(context).load(url).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade()
                .transform(new FGlideCircleTransform(context)).into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Url=" + url + ", ImageView=" +
                imageView.toString());
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

    public void loadCircleLocalImage(Context context, String path, ImageView imageView)
    {
        Glide.with(context).load("file://" + path).placeholder(R.color.f_transparent_1a)
                .error(R.color.f_transparent_1a).crossFade()
                .transform(new FGlideCircleTransform(context)).into(imageView);
        FLog.i("Context=" + context.getClass().getName() + ", Path=" + path + ", ImageView=" +
                imageView.toString());
    }
}
