package com.playloudr.app.model.client.config

data class ClientConfig(
  val aws: AwsConfig
) {
  data class AwsConfig(
    val region: String,
    val identityPoolId: String,
    val tableName: String
  )
}
