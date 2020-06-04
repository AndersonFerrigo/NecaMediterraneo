package com.pt.necadomediterraneo.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.pt.necadomediterraneo.R;

public class NecaMediterraneoWebView extends AppCompatActivity {

    private WebView myWebView;
    private ImageView splashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neca_mediterraneo_webview);
        // In Activity's onCreate() for instance
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        myWebView = findViewById(R.id.myWebView);
        splashView = findViewById(R.id.splash_screen);

        WebSettings webSettings = myWebView.getSettings();

        //Habilitando o JavaScript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                splashView.setVisibility(View.GONE);
                myWebView.setVisibility(View.VISIBLE);
            }
        });

        //new link to webview
        myWebView.loadUrl("https://churrasqueira-onecadavenda.pt/?fbclid=IwAR269YMf3RKBKUad1VegyX5jxMtfMSM3lvLefEEgzD7mneNv6hb8L0xip-s");
        myWebView.setVisibility(View.GONE);
        splashView.setVisibility(View.VISIBLE);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
