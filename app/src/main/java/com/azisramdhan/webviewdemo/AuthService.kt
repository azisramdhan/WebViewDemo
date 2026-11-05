package com.azisramdhan.webviewdemo

/** The one account the demo accepts, matching the subtitle on the login screen. */
object AuthService {
    fun signIn(username: String, password: String): Boolean =
        username.trim() == "admin" && password == "password"
}
