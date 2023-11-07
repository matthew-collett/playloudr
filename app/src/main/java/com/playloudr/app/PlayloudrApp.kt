package com.playloudr.app

import android.app.Application
import com.playloudr.app.model.client.aws.AwsClientManager
import com.playloudr.app.model.client.config.ConfigProvider
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

class PlayloudrApp : Application() {
  override fun onCreate() {
    super.onCreate()
    ConfigProvider.init(ctx = this)
  }

  override fun onTerminate() {
    super.onTerminate()
    AwsClientManager.closeClients()
  }
}