package com.azisramdhan.webviewdemo

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/** The counterpart of the iOS UIWebView migration notes. */
class LegacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val density = resources.displayMetrics.density
        fun dp(value: Int) = (value * density).toInt()

        val notes = listOf(
            "android.webkit.WebView is the only web view Android ships. The migration here is from the old synchronous bridge to an evaluateJavascript callback.",
            "addJavascriptInterface exposes Kotlin to the page. Everything it returns must be a String, so the device profile arrives as JSON.",
            "setWebContentsDebuggingEnabled opens the page to Chrome DevTools, which is the Android equivalent of attaching Safari to a WKWebView.",
            "Loupe captures this screen to prove that a navigation two taps deep still reaches the pull request."
        )

        val column = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(24), dp(24), dp(24), dp(24))
            addView(TextView(this@LegacyActivity).apply {
                text = getString(R.string.title_legacy)
                textSize = 24f
                gravity = Gravity.START
                contentDescription = getString(R.string.title_legacy)
            })
            notes.forEach { note ->
                addView(TextView(this@LegacyActivity).apply {
                    text = note
                    textSize = 15f
                }, LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { topMargin = dp(20) })
            }
        }
        setContentView(ScrollView(this).apply { addView(column) })
    }
}
