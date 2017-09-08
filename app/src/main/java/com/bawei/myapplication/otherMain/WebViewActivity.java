package com.bawei.myapplication.otherMain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.bawei.myapplication.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        getSupportActionBar().hide();
        WebView ev= (WebView) findViewById(R.id.wb);
        Intent intent=getIntent();
        String s = intent.getStringExtra("url");
        System.out.println("----------------------"+s);
        ev.loadUrl(s);


    }
}
