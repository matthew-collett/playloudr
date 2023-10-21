package com.playloudr.app.model.dao

import android.util.Log
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.model.DynamoDbException
import aws.sdk.kotlin.services.dynamodb.model.GetItemRequest
import aws.sdk.kotlin.services.dynamodb.model.GetItemResponse
import aws.sdk.kotlin.services.dynamodb.model.PutItemRequest
import aws.sdk.kotlin.services.dynamodb.model.PutItemResponse
import aws.sdk.kotlin.services.dynamodb.model.QueryRequest
import aws.sdk.kotlin.services.dynamodb.model.QueryResponse
import com.playloudr.app.model.client.config.ConfigProvider

class DynamoDbDao : AbstractDao<DynamoDbClient>(DynamoDbClient::class) {
  companion object { const val TAG: String = "[DynamoDb Error]: " }
  private val dynamoDbTableName = ConfigProvider.getReceiver().aws.dynamoDbTableName

  suspend fun query(
    keyCondition: String,
    attributes: Map<String, AttributeValue>
  ): List<Map<String, AttributeValue>> {
    val items: MutableList<Map<String, AttributeValue>> = mutableListOf()
    try {
      var lastEvaluatedKey: Map<String, AttributeValue>? = null
      do {
        val request = QueryRequest {
          this.tableName = dynamoDbTableName
          keyConditionExpression = keyCondition
          expressionAttributeValues = attributes
          exclusiveStartKey = lastEvaluatedKey
        }
        val response: QueryResponse = getClient().query(request)
        response.items?.let { items.addAll(it) }
        lastEvaluatedKey = response.lastEvaluatedKey
      } while (!lastEvaluatedKey.isNullOrEmpty())
      return items
    } catch (e: DynamoDbException) {
      Log.w(TAG, "Unable to query table. ${e.message}", e)
      throw e
    }
  }

  suspend fun getItem(itemValues: Map<String, AttributeValue>): Map<String, AttributeValue>? {
    val request = GetItemRequest {
      tableName = dynamoDbTableName
      key = itemValues
    }
    return try {
      getClient().getItem(request).item
    } catch (e: DynamoDbException) {
      Log.w(TAG, "Unable to get item from table. ${e.message}", e)
      throw e
    }
  }

  suspend fun putItem(itemValues: Map<String, AttributeValue>) {
    val request = PutItemRequest {
      tableName = dynamoDbTableName
      item = itemValues
    }
    try {
      getClient().putItem(request)
    } catch (e: DynamoDbException) {
      Log.w(TAG, "Unable to put item in table. ${e.message}", e)
      throw e
    }
  }
}

