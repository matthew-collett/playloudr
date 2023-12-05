package com.playloudr.app.model.client.spotify

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SpotifyServiceClient {
  private val client: SpotifyClient by lazy {
    createClient()
  }

  fun getSpotify(): SpotifyClient {
    return client
  }

  private fun createClient(): SpotifyClient {
    val okHttpClient = OkHttpClient.Builder()
      .addInterceptor(SpotifyAuthInterceptor(SpotifyTokenManager))
      .build()

    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.spotify.com/v1/")
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    return retrofit.create(SpotifyClient::class.java)
  }
}

