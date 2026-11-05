package com.azisramdhan.webviewdemo

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthServiceTest {

    @Test
    fun `accepts the demo account`() {
        assertTrue(AuthService.signIn("admin", "password"))
    }

    @Test
    fun `ignores surrounding whitespace in the username`() {
        assertTrue(AuthService.signIn("  admin  ", "password"))
    }

    @Test
    fun `rejects a wrong password`() {
        assertFalse(AuthService.signIn("admin", "Password"))
    }

    @Test
    fun `rejects an unknown user`() {
        assertFalse(AuthService.signIn("root", "password"))
    }
}
