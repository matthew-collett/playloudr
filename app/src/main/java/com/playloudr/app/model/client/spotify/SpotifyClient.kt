package com.playloudr.app.model.client.spotify

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotifyClient {

  @GET("search")
  suspend fun query(
    @Query("q") query: String,
    @Query("type") type: String,
    @Query("limit") limit: Int? = null,
    @Query("offset") offset: Int? = null
  ): Response<SpotifyResponse>
}
