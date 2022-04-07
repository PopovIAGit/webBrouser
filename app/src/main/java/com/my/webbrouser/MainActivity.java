package com.my.webbrouser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout;
    private EditText editText;
    private Button button;
    private WebView  webView;

    private String savedAddr = "http://ya.ru";

    public static final String ADDR = "ADDR";

public static final int WRAP = ViewGroup.LayoutParams.WRAP_CONTENT;
    public static final int MATCH = ViewGroup.LayoutParams.MATCH_PARENT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        editText = new EditText(this);
        editText.setLayoutParams( new ViewGroup.LayoutParams(MATCH,WRAP));
        editText.setText(savedAddr);
        editText.setId(R.id.url_id);

        button = new Button(this);
        button.setLayoutParams( new ViewGroup.LayoutParams(WRAP,WRAP));
        button.setText("Press me to go");
        button.setOnClickListener(this);

        webView = new WebView(this);
        webView.setLayoutParams(new ViewGroup.LayoutParams(MATCH, MATCH));
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webView.setWebViewClient(new WebViewClient());

        linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams( new ViewGroup.LayoutParams(MATCH,MATCH));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(editText);
        linearLayout.addView(button);
        linearLayout.addView(webView);

        setContentView(linearLayout);

    }

    @Override
    public void onClick(View view) {

        String address = editText.getText().toString();
        if (!address.isEmpty())
        try {
            new URL(address);
            webView.loadUrl(address);
        }catch(MalformedURLException e)
        {
            e.printStackTrace();
            Log.d("Happy","invalid URL");

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        onClick(button);
    }

    /*
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putString(ADDR, editText.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedAddr = savedInstanceState.getString(ADDR);
        editText.setText(savedInstanceState.getString(ADDR));

        try {
            new URL(savedAddr);
            webView.loadUrl(savedAddr);
        }catch(MalformedURLException e)
        {
            e.printStackTrace();
            Log.d("Happy","invalid URL");

        }


    }*/
}