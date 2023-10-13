package com.playloudr.app

import android.app.Application
import com.playloudr.app.model.client.aws.AwsClientProvider
import com.playloudr.app.model.client.config.ConfigProvider

class PlayloudrApp : Application() {
  override fun onCreate() {
    super.onCreate()
    ConfigProvider.init(ctx = this)
    AwsClientProvider.init(ctx = this)
  }

  override fun onTerminate() {
    super.onTerminate()
    AwsClientProvider.get().closeClients()
  }
}