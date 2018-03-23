package com.android.prince.getmyrepo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WebView extends AppCompatActivity {

    private String repoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        repoUrl = intent.getStringExtra("URL");

        android.webkit.WebView
    }
}
