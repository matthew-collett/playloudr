package com.playloudr.app.data.client.config

data class ClientConfig(
  val aws: AwsConfig
) {
  data class AwsConfig(
    val region: String,
    val identityPoolId: String,
    val tableName: String
  )
}
