package com.playloudr.app.model.client.aws

import android.content.Context
import com.playloudr.app.model.Provider

object AwsClientProvider : Provider<AwsClientManager>() {
  override fun create(ctx: Context): AwsClientManager {
    return AwsClientManager()
  }
}
