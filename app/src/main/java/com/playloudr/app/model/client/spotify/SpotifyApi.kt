package com.playlouder.app.client.spotify

import com.playloudr.app.model.entity.SpotifyAccessTokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

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
