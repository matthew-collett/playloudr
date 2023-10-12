package com.playloudr.app.data.client.aws

import android.content.Context
import com.playloudr.app.data.Provider
import com.playloudr.app.data.client.config.ConfigProvider

object AwsClientProvider : Provider<AwsClientManager>() {
  override fun create(ctx: Context): AwsClientManager {
    val clientConfig = ConfigProvider.get()
    return AwsClientManager(config = clientConfig)
  }
}
