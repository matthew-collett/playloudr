package com.playloudr.app.model.dao

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.model.GetItemRequest
import aws.sdk.kotlin.services.dynamodb.model.PutItemRequest
import aws.sdk.kotlin.services.dynamodb.model.QueryRequest
import aws.sdk.kotlin.services.dynamodb.model.QueryResponse
import com.playloudr.app.model.client.aws.AwsClientManager
import com.playloudr.app.model.client.config.ConfigProvider

class DynamoDbDao {
  private val dynamoDbTableName = ConfigProvider.get().aws.dynamoDbTableName
  private val client = AwsClientManager.getButtFace()

  suspend fun query(
    keyCondition: String,
    attributes: Map<String, AttributeValue>
  ): List<Map<String, AttributeValue>> {
    val items: MutableList<Map<String, AttributeValue>> = mutableListOf()
    var lastEvaluatedKey: Map<String, AttributeValue>? = null
    do {
      val request = QueryRequest {
        this.tableName = dynamoDbTableName
        keyConditionExpression = keyCondition
        expressionAttributeValues = attributes
        exclusiveStartKey = lastEvaluatedKey
      }
      val response: QueryResponse = client.query(request)
      response.items?.let { items.addAll(it) }
      lastEvaluatedKey = response.lastEvaluatedKey
    } while (!lastEvaluatedKey.isNullOrEmpty())
    return items
  }

  suspend fun getItem(itemValues: Map<String, AttributeValue>): Map<String, AttributeValue>? {
    val request = GetItemRequest {
      tableName = dynamoDbTableName
      key = itemValues
    }
    return client.getItem(request).item
  }

  suspend fun putItem(itemValues: Map<String, AttributeValue>) {
    val request = PutItemRequest {
      tableName = dynamoDbTableName
      item = itemValues
    }
    client.putItem(request)
  }
}

