package com.playloudr.app.model.client.config

data class ClientConfig(
  val aws: AwsConfig,
  val spotify: SpotifyConfig
) {
  data class AwsConfig(
    val region: String,
    val identityPoolId: String,
    val dynamoDbTableName: String,
    val s3BucketName: String
  )
  data class SpotifyConfig(
    val clientId: String,
    val clientSecret: String
  )
}
