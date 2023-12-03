package com.playloudr.app.model.client.spotify

import android.util.Base64
import com.playloudr.app.model.dao.SecretsManagerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

object SpotifyTokenManager {
  @Volatile
  private var accessToken: String? = null
  @Volatile
  private var refreshToken: String? = null
  private val lock = Any()
  private var accessTokenExpiryTime: Long = 0
  private var clientId: String? = null
  private var clientSecret: String? = null
  private lateinit var okHttpClient: OkHttpClient

  suspend fun init(client: OkHttpClient) {
    okHttpClient = client
    if (clientId == null || clientSecret == null) {
      clientId = SecretsManagerDao().getSecret("client_id")
      clientSecret = SecretsManagerDao().getSecret("client_secret")
    }
  }

  suspend fun getToken(): String {
    val currentRefreshToken: String
    synchronized(lock) {
      if (accessToken == null || tokenIsExpired()) {
        currentRefreshToken = refreshToken
          ?: throw IllegalStateException("Refresh token not available")
      } else {
        return accessToken!!
      }
    }
    return refreshAccessToken(currentRefreshToken)
  }

  fun setToken(newAccessToken: String, newRefreshToken: String, expiresIn: Long) {
    synchronized(lock) {
      accessToken = newAccessToken
      refreshToken = newRefreshToken
      accessTokenExpiryTime = System.currentTimeMillis() / 1000 + expiresIn
    }
  }

  private fun tokenIsExpired(): Boolean {
    val currentTime = System.currentTimeMillis() / 1000
    return currentTime >= accessTokenExpiryTime
  }

  private suspend fun refreshAccessToken(refreshToken: String): String {
    val url = "https://accounts.spotify.com/api/token"
    val credentials = "$clientId:$clientSecret"
    val encodedCredentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

    val requestBody = FormBody.Builder()
      .add("grant_type", "refresh_token")
      .add("refresh_token", refreshToken)
      .build()

    val request = Request.Builder()
      .url(url)
      .post(requestBody)
      .header("Authorization", "Basic $encodedCredentials")
      .header("Content-Type", "application/x-www-form-urlencoded")
      .build()

    return withContext(Dispatchers.IO) {
      val response = okHttpClient.newCall(request).execute()
      if (response.isSuccessful) {
        val responseBody = response.body.string()
        val jsonObject = JSONObject(responseBody)
        this@SpotifyTokenManager.refreshToken = jsonObject.optString("refresh_token", refreshToken)
        jsonObject.getString("access_token")
      } else {
        throw Exception("Failed to refresh token")
      }
    }
  }
}
