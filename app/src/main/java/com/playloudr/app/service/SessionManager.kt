package com.playloudr.app.service

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
  private lateinit var sharedPreferences: SharedPreferences

  fun init(ctx: Context) {
    sharedPreferences = ctx.getSharedPreferences("UserSessionPrefs", Context.MODE_PRIVATE)
  }

  fun loginUser(username: String) {
    sharedPreferences.edit().putString("username", username).apply()
  }

  fun getCurrentUser(): String? {
    return sharedPreferences.getString("username", null)
  }

  fun isLoggedIn(): Boolean {
    return getCurrentUser() != null
  }

  fun logoutUser() {
    sharedPreferences.edit().remove("username").apply()
  }
}
