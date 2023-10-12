package com.playloudr.app.data.client.config

import android.content.Context
import com.playloudr.app.R
import com.playloudr.app.data.Provider
import com.typesafe.config.ConfigFactory

object ConfigProvider : Provider<ClientConfig>() {
  override fun create(ctx: Context): ClientConfig {
    val inputStream = ctx.resources.openRawResource(R.raw.application)
    val confString = inputStream.bufferedReader().use { it.readText() }
    val config = ConfigFactory.parseString(confString)
    return ClientConfig(
      aws = ClientConfig.AwsConfig(
        region = config.getString("aws.region"),
        identityPoolId = config.getString("aws.identityPoolId"),
        tableName = config.getString("aws.tableName")
      )
    )
  }
}