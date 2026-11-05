package com.azisramdhan.webviewdemo

import android.app.Activity
import android.webkit.JavascriptInterface
import android.widget.Toast
import org.json.JSONObject

/** The asset page crosses into the app through these methods and nothing else. */
class JsBridge(private val activity: Activity) {

    @JavascriptInterface
    fun showToast(message: String) {
        activity.runOnUiThread { Toast.makeText(activity, message, Toast.LENGTH_SHORT).show() }
    }

    @JavascriptInterface
    fun openNativeScreen(name: String) {
        activity.runOnUiThread { Toast.makeText(activity, "Native screen: $name", Toast.LENGTH_SHORT).show() }
    }

    @JavascriptInterface
    fun requestDeviceData(): String = JSONObject()
        .put("platform", "Android")
        .put("model", android.os.Build.MODEL)
        .put("release", android.os.Build.VERSION.RELEASE)
        .toString()
}
