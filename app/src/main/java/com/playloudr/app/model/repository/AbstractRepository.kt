package com.playloudr.app.model.repository

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import com.playloudr.app.model.dao.DynamoDbDao
import com.playloudr.app.util.Constants.DynamoDB.KEY_NAME_PK
import com.playloudr.app.util.Constants.DynamoDB.KEY_NAME_SK
import com.playloudr.app.util.Constants.DynamoDB.KEY_PREFIX_USER

abstract class AbstractRepository<T> {
  protected val dynamoDbDao: DynamoDbDao = DynamoDbDao()

  protected suspend fun dynamoPrefixQuery(username: String, skPrefix: String): List<T> {
    val keyCondition = "$KEY_NAME_PK = :pk AND begins_with($KEY_NAME_SK, :skprefix)"
    val attributes: Map<String, AttributeValue> = mutableMapOf(
      ":pk" to AttributeValue.S(KEY_PREFIX_USER + username),
      ":skprefix" to AttributeValue.S(skPrefix)
    )
    val items: List<Map<String, AttributeValue>> = dynamoDbDao.query(keyCondition, attributes)
    return items.map { builder(it) }
  }
  protected suspend fun dynamoShallowPrefixQuery(username: String, skPrefix: String): List<String> {
    val keyCondition = "$KEY_NAME_PK = :pk AND begins_with($KEY_NAME_SK, :skprefix)"
    val attributes: Map<String, AttributeValue> = mutableMapOf(
      ":pk" to AttributeValue.S(KEY_PREFIX_USER + username),
      ":skprefix" to AttributeValue.S(skPrefix)
    )
    val items: List<Map<String, AttributeValue>> = dynamoDbDao.query(keyCondition, attributes)
    return items.mapNotNull { it -> it[KEY_NAME_SK]?.asS()?.let { resolveKeyName(it) } }
  }

  abstract fun builder(entityValues: Map<String, AttributeValue>): T

  protected fun resolveKeyName(keyName: String): String {
    return keyName.substringAfter("#")
  }
}