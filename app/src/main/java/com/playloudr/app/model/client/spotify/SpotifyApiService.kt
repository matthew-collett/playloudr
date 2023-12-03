package com.playloudr.app.model.client.spotify

import retrofit2.http.GET

interface SpotifyApiService {
  @GET("search")
  suspend fun buildRequest(): RequestBuilder

  interface RequestBuilder {
    fun query(query: String): RequestBuilder
    fun type(type: String): RequestBuilder
    suspend fun execute(): Response<SearchResponse> // need to implement
  }
}
