package com.azisramdhan.webviewdemo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat

/** Shows the bundled asset page and offers the Legacy screen, as the iOS browser does. */
class LocalDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val web = WebView(this).apply {
            contentDescription = "Web View"
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            addJavascriptInterface(JsBridge(this@LocalDemoActivity), "Bridge")
            loadUrl("file:///android_asset/index.html")
        }
        setContentView(web)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val item = menu.add(Menu.NONE, LEGACY_ITEM, Menu.NONE, R.string.legacy)
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        // Appium matches an accessibility id against the content description.
        MenuItemCompat.setContentDescription(item, getString(R.string.legacy))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId != LEGACY_ITEM) return super.onOptionsItemSelected(item)
        startActivity(Intent(this, LegacyActivity::class.java))
        return true
    }

    private companion object {
        const val LEGACY_ITEM = 1
    }
}
