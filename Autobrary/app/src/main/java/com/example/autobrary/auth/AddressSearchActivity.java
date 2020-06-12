package com.example.autobrary.auth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.autobrary.R;

import cz.msebera.android.httpclient.util.EncodingUtils;

public class AddressSearchActivity extends AppCompatActivity {

    private WebView webView;
    private TextView result;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_search);
        result = (TextView) findViewById(R.id.result);
        // WebView 초기화
        init_webView();
        // 핸들러를 통한 JavaScript 이벤트 반응
        handler = new Handler();
    }

    @JavascriptInterface
    public void init_webView() {
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.addJavascriptInterface(new AndroidBridge(), "slo");
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("https://slobrary.com/app/getAddress.php");
    }

    public class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    result.setText(String.format("(%s) %s %s", arg1, arg2, arg3));
                    init_webView();
                }
            });
        }
    }
}
