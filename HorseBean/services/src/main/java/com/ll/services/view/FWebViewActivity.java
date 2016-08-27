package com.ll.services.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ll.services.R;
import com.ll.services.view.titlebar.IFTitlebar;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FWebViewActivity extends FBaseActivity
{
    private static final int PROGRESS_RATIO = 1000;
    private ProgressBar mWebviewPb;
    private WebView mWebView;

    @Override protected int getLayoutResource()
    {
        return R.layout.f_activity_webview;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(FWebViewIntent.getTitle(getIntent()));
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {
        mWebviewPb = (ProgressBar) this.findViewById(R.id.f_activity_webview_pb);
        mWebView = (WebView) this.findViewById(R.id.f_activity_webview);
        this.enableJavascript();
        this.enableCaching();
        this.enableCustomClients();
        this.enableAdjust();
        this.zoomedOut();
        this.mWebView.loadUrl(FWebViewIntent.getUrl(getIntent()));
    }

    @Override protected void loadData()
    {
    }

    @SuppressLint("SetJavaScriptEnabled") private void enableJavascript()
    {
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    private void enableCaching()
    {
        this.mWebView.getSettings().setAppCachePath(getFilesDir() + getPackageName() + "/cache");
        this.mWebView.getSettings().setAppCacheEnabled(true);
        this.mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
    }

    private void enableAdjust()
    {
        this.mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    private void zoomedOut()
    {
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.getSettings().setSupportZoom(true);
    }

    private void enableCustomClients()
    {
        this.mWebView.setWebViewClient(new WebViewClient()
        {
            @Override public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }

            @Override public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
            }
        });
        this.mWebView.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view, int progress)
            {
                FWebViewActivity.this.mWebviewPb.setProgress(progress);
                setProgress(progress * PROGRESS_RATIO);
                if (progress >= 80)
                {
                    FWebViewActivity.this.mWebviewPb.setVisibility(View.GONE);
                }
                else
                {
                    FWebViewActivity.this.mWebviewPb.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override protected void onDestroy()
    {
        super.onDestroy();
        if (this.mWebView != null)
        {
            this.mWebView.destroy();
        }
    }
}
