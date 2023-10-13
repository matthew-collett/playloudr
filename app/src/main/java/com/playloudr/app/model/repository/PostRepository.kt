package com.playloudr.app.model.repository

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.model.GetItemRequest
import com.playloudr.app.model.client.aws.AwsClientProvider
import com.playloudr.app.model.client.config.ConfigProvider

class PostRepository {

  suspend fun getItem() {
    val request = GetItemRequest {
      tableName = ConfigProvider.get().aws.tableName
      key = mapOf(
        "PK" to AttributeValue.S("USER#eric.cuenat"),
        "SK" to AttributeValue.S("METADATA#eric.cuenat")
      )
    }
    val response = AwsClientProvider.get().getDynamoDb().getClient().getItem(request)
    println(response)
  }
}