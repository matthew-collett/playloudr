package com.playloudr.app.model.dao

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.model.QueryRequest
import com.playloudr.app.model.client.config.ConfigProvider

class DynamoDbDao {
  suspend fun query(keyCondition: String, attributes: Map<String, AttributeValue.S>) {
    val request = QueryRequest {
      tableName = ConfigProvider.getReceiver().aws.dynamoDbTableName
      keyConditionExpression = keyCondition
      expressionAttributeValues = attributes
    }



  }
}