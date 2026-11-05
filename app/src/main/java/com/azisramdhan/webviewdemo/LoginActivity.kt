package com.azisramdhan.webviewdemo

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameField: EditText
    private lateinit var passwordField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(buildLayout())
    }

    private fun buildLayout(): View {
        val density = resources.displayMetrics.density
        fun dp(value: Int) = (value * density).toInt()

        val heading = TextView(this).apply {
            text = getString(R.string.heading)
            textSize = 28f
            gravity = Gravity.CENTER
        }
        val subtitle = TextView(this).apply {
            text = getString(R.string.credentials)
            textSize = 14f
            gravity = Gravity.CENTER
        }
        usernameField = EditText(this).apply {
            hint = getString(R.string.username)
            contentDescription = getString(R.string.username)
            inputType = InputType.TYPE_CLASS_TEXT
        }
        passwordField = EditText(this).apply {
            hint = getString(R.string.password)
            contentDescription = getString(R.string.password)
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        val logIn = Button(this).apply {
            text = getString(R.string.log_in)
            contentDescription = getString(R.string.log_in)
            setOnClickListener { signIn() }
        }
        val localDemo = Button(this).apply {
            text = getString(R.string.skip_to_local_demo)
            contentDescription = getString(R.string.skip_to_local_demo)
            setOnClickListener { start(LocalDemoActivity::class.java) }
        }

        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(32), dp(24), dp(32), dp(24))
            addView(heading)
            addView(subtitle, spacing(dp(40)))
            addView(usernameField, spacing(dp(20)))
            addView(passwordField, spacing(dp(20)))
            addView(logIn, spacing(dp(20)))
            addView(localDemo, spacing(dp(20)))
        }
    }

    private fun spacing(top: Int) = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply { topMargin = top }

    private fun signIn() {
        if (AuthService.signIn(usernameField.text.toString(), passwordField.text.toString())) {
            start(WebActivity::class.java)
        } else {
            Toast.makeText(this, R.string.bad_credentials, Toast.LENGTH_SHORT).show()
        }
    }

    private fun start(target: Class<*>) = startActivity(Intent(this, target))
}
