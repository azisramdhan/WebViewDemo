package com.azisramdhan.webviewdemo

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

/** Reached after a successful sign-in, mirroring the iOS browser screen. */
class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val web = WebView(this).apply {
            contentDescription = "Web View"
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("https://example.com")
        }
        setContentView(web)
    }
}
