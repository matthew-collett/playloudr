package com.playloudr.app

import com.playloudr.app.model.client.spotify.SpotifyTokenManager
import com.playloudr.app.model.dao.SpotifyDao
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient

fun main() = runBlocking {
  SpotifyTokenManager.init(OkHttpClient.Builder().build())
  SpotifyDao().queryTest()
}
