package com.playloudr.app.model.dao

import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.content.asByteStream
import com.playloudr.app.model.client.aws.AwsClientManager
import com.playloudr.app.model.client.config.ConfigProvider
import java.io.File


object S3Dao {
  private val config = ConfigProvider.get().aws
  private val bucketName = config.s3BucketName
  private val client = AwsClientManager.getS3()

  suspend fun putObject(path: String, key: String) {
    val request = PutObjectRequest {
      bucket = bucketName
      this.key = key
      body = File(path).asByteStream()
    }
    client.putObject(request)
  }

  fun getS3Url(key: String): String {
    return "https://$bucketName.s3.${config.region}.amazonaws.com/$key"
  }
}