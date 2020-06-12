package com.example.autobrary.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.autobrary.R;

import cz.msebera.android.httpclient.util.EncodingUtils;

public class AddressSearchActivity extends AppCompatActivity {

    private WebView webView;
    private TextView result;
    private Handler handler;
    private Button conform;
    private EditText detailAddr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_search);
        result = (TextView) findViewById(R.id.result);
        detailAddr = (EditText) findViewById(R.id.detailAddr);
        // WebView 초기화
        init_webView();
        // 핸들러를 통한 JavaScript 이벤트 반응
        handler = new Handler();
        conform = findViewById(R.id.conform);
        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", result.getText().toString() + detailAddr.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @JavascriptInterface
    public void init_webView() {
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.addJavascriptInterface(new AndroidBridge(), "slo");
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("https://slobrary.com/app/getAddress.html");
    }

    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    result.setText(String.format("(%s) %s %s", arg1, arg2, arg3));
                }
            });
        }
    }
}
