package com.android.prince.getmyrepo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewAct extends AppCompatActivity {

    private String repoUrl;

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        repoUrl = intent.getStringExtra("URL");

        webView = (WebView)findViewById(R.id.WebView);
        webView.loadUrl(repoUrl);


    }
}
