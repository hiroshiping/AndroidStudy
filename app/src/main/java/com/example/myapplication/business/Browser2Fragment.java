package com.example.myapplication.business;

import android.app.Fragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.example.myapplication.R;


public class Browser2Fragment extends Fragment {

    public final String USER_AGENT = "Mozilla/5.0 (Linux; Android 10; SCV45) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.111 Mobile Safari/537.36";

    public static Browser2Fragment newInstance() {
        Browser2Fragment fragment = new Browser2Fragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_browser2, container, false);

        WebView webView = (WebView) view.findViewById(R.id.wv_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setUserAgentString(USER_AGENT);

        webView.loadUrl("https://developer.android.com/?hl=ja");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                view.loadUrl(url);
                //返回true
                return true;
            }
        });

        return view;
    }
}