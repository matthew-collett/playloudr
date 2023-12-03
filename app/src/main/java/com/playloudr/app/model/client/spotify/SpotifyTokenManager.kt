package com.playloudr.app.model.client.spotify

import android.util.Base64
import com.playloudr.app.model.dao.SecretsManagerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

object SpotifyTokenManager {
  @Volatile
  private var accessToken: String? = null
  private val lock = Any()
  private val mutex = Mutex()
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
    mutex.withLock {
      if (accessToken == null || tokenIsExpired()) {
        fetchAndStoreAccessToken()
      }
      return accessToken!!
    }
  }

  private suspend fun fetchAndStoreAccessToken() {
    val tokenResponse = refreshAccessToken()
    synchronized(lock) {
      accessToken = tokenResponse.accessToken
      accessTokenExpiryTime = System.currentTimeMillis() / 1000 + tokenResponse.expiresIn
    }
  }

  private suspend fun refreshAccessToken(): TokenResponse {
    val auth = Base64.encodeToString("$clientId:$clientSecret".toByteArray(), Base64.NO_WRAP)
    val requestBody = FormBody.Builder()
      .add("grant_type", "client_credentials")
      .build()

    val request = Request.Builder()
      .url("https://accounts.spotify.com/api/token")
      .post(requestBody)
      .header("Authorization", "Basic $auth")
      .build()

    return withContext(Dispatchers.IO) {
      okHttpClient.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        val jsonObject = JSONObject(response.body.string())
        TokenResponse(
          accessToken = jsonObject.getString("access_token"),
          expiresIn = jsonObject.getInt("expires_in")
        )
      }
    }
  }

  private fun tokenIsExpired(): Boolean {
    val currentTime = System.currentTimeMillis() / 1000
    return currentTime >= accessTokenExpiryTime
  }

  data class TokenResponse(
    val accessToken: String,
    val expiresIn: Int
  )
}
