package com.playloudr.app.model.client.spotify

import com.playloudr.app.model.client.AbstractClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SpotifyServiceClient : AbstractClient<SpotifyApiService>() {
  override fun createClient(): SpotifyApiService {
    val okHttpClient = OkHttpClient.Builder()
      .addInterceptor(SpotifyAuthInterceptor(SpotifyTokenManager))
      .build()
    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.spotify.com/v1/")
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    return retrofit.create(SpotifyApiService::class.java)
  }

  override fun closeClient() {
    return
  }
}
