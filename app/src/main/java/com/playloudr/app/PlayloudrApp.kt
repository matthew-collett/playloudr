package com.playloudr.app

import android.app.Application
import com.playloudr.app.model.client.aws.AwsClientManager
import com.playloudr.app.model.client.config.ConfigProvider
import com.playloudr.app.model.client.spotify.SpotifyTokenManager
import com.playloudr.app.model.dao.SpotifyDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class PlayloudrApp : Application() {
  override fun onCreate() {
    super.onCreate()
    ConfigProvider.init(ctx = this)
    AwsClientManager
    CoroutineScope(Dispatchers.IO).launch {
      SpotifyTokenManager.init(OkHttpClient.Builder().build())
    }
  }
}
