package com.playloudr.app.model.client.spotify

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class SpotifyAuthInterceptor(private val tokenManager: SpotifyTokenManager) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val accessToken = runBlocking(Dispatchers.IO) {
      tokenManager.getToken()
    }

    val authenticatedRequest = request.newBuilder()
      .header("Authorization", "Bearer $accessToken")
      .build()
    return chain.proceed(authenticatedRequest)
  }
}
