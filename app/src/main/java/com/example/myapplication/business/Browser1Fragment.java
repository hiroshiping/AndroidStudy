package com.example.myapplication.business;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;


import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapplication.R;

public class Browser1Fragment extends Fragment {

    public final String USER_AGENT = "Mozilla/5.0 (Linux; Android 10; SCV45) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.111 Mobile Safari/537.36";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Browser1Fragment() {
        // Required empty public constructor
    }

    public static Browser1Fragment newInstance() {
        Browser1Fragment fragment = new Browser1Fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browser1, container, false);

        WebView webView = (WebView) view.findViewById(R.id.wv_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);// 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setUserAgentString(USER_AGENT);

        webView.loadUrl("https://www.forestsoft.jp/roundcube/");
//        webSettings.setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.d("debug", "onJsAlert");
                if (message != null) {
                    Log.d("debug", message);
                }
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                Log.d("debug", "onShowFileChooser");
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                Log.d("debug", "onJsPrompt");
                if (message != null) {
                    Log.d("debug", message);
                }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                Log.d("debug", "onJsBeforeUnload");

                if (message != null) {
                    Log.d("debug", message);
                }
                return super.onJsBeforeUnload(view, url, message, result);
            }

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                Log.d("debug", "onCreateWindow");

                if (resultMsg != null) {
                    Log.d("debug", resultMsg.toString());
                }

                return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                Log.d("debug", "onJsConfirm");

                if (message != null) {
                    Log.d("debug", message);
                }
                return super.onJsConfirm(view, url, message, result);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                view.loadUrl(url);
                //返回true
                return true;
            }

            //            https://blog.csdn.net/cl1771066100/article/details/53054596
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:(function(){" +
                        "var user = document.getElementById(\"rcmloginuser\"); " +
                        "var psw = document.getElementById(\"rcmloginpwd\"); " +
                        " user.value = \"heikou\";" +
                        " psw.value = \"g5MjEzYT\";" +
                        "var clickBtn = document.getElementById(\"rcmloginsubmit\"); " +
                        " clickBtn.click(); " +
                        "})()");
                super.onPageFinished(view, url);
            }
        });
        return view;
    }
}