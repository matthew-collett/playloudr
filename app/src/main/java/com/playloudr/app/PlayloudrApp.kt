package com.playloudr.app

import android.app.Application
import com.playloudr.app.model.client.aws.AwsClientManager
import com.playloudr.app.model.client.config.ConfigProvider

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