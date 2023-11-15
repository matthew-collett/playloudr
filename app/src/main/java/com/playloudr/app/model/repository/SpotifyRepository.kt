package com.playloudr.app.model.repository

import com.playlouder.app.client.spotify.SpotifyApi
import com.playloudr.app.model.dao.SecretsManagerDao
import com.playloudr.app.model.repository.SpotifyRepository.RetrofitInstance.retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Base64


class SpotifyRepository() {
  private val secretsManagerDao = SecretsManagerDao()
  suspend fun getSongImageUrl(songName: String): String? {
    //return SpotifyApi.searchSong(songName);
    return null

  }

  object RetrofitInstance {
    val retrofit: Retrofit = Retrofit.Builder()
      .baseUrl("https://accounts.spotify.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
  suspend fun getSpotifyAccessToken(): String? {
    val clientId = secretsManagerDao.getSecret("client_id")
    val clientSecret = secretsManagerDao.getSecret("client_secret")

    // Encode the client ID and secret in base64 as required for Spotify's API
    val authHeader = Base64.getEncoder().encodeToString("$clientId:$clientSecret".toByteArray())

    // Create a request to get the access token
    val response = retrofit.create(SpotifyApi.SpotifyService::class.java).getAccessToken(
      "Basic $authHeader",
      "client_credentials"
    )

    return if (response.isSuccessful) {
      response.body()?.accessToken
    } else {
      null
    }
  }

  suspend fun searchSong(songName: String): String? {
    // Logic to search a song using the access token
    return null
  }

}
