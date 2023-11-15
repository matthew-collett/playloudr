package com.playlouder.app.client.spotify

import android.util.Log
import com.playloudr.app.model.dao.SecretsManagerDao
import com.playloudr.app.model.entities.SpotifyAccessTokenResponse
import com.playloudr.app.model.entities.SpotifySearchResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

object SpotifyApi {

  interface SpotifyService {
    @FormUrlEncoded
    @POST("api/token")
    suspend fun getAccessToken(
      @Header("Authorization") authHeader: String,
      @Field("grant_type") grantType: String
    ): Response<SpotifyAccessTokenResponse>
  }

}
