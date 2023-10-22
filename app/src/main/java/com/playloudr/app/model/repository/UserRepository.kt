package com.playloudr.app.model.repository

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import com.playloudr.app.model.entities.UserEntity
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_BIO
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_DISPLAY_NAME
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_EMAIL
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_PASSWORD
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_PROFILE_PICTURE_URL
import com.playloudr.app.util.Constants.DynamoDB.KEY_NAME_PK
import com.playloudr.app.util.Constants.DynamoDB.KEY_NAME_SK
import com.playloudr.app.util.Constants.DynamoDB.KEY_PREFIX_FOLLOWING
import com.playloudr.app.util.Constants.DynamoDB.KEY_PREFIX_METADATA
import com.playloudr.app.util.Constants.DynamoDB.KEY_PREFIX_USER

object UserRepository : AbstractRepository<UserEntity>() {

  suspend fun getUserProfile(username: String): UserEntity? {
    val primaryKey: Map<String, AttributeValue> = mutableMapOf(
      KEY_NAME_PK to AttributeValue.S(KEY_PREFIX_USER + username),
      KEY_NAME_SK to AttributeValue.S(KEY_PREFIX_METADATA + username)
    )
    val response: Map<String, AttributeValue>? = dynamoDbDao.getItem(primaryKey)
    return response?.let { builder(it) }
  }

  suspend fun getUserFollowing(username: String): List<String> {
    return shallowPrefixQuery(username, KEY_PREFIX_FOLLOWING)
  }

  override fun builder(entityValues: Map<String, AttributeValue>): UserEntity {
    return UserEntity(
      username = resolveKeyName(entityValues[KEY_NAME_PK]!!.asS()),
      profilePictureUrl = entityValues[ATTRIBUTE_NAME_PROFILE_PICTURE_URL]!!.asS(),
      displayName = entityValues[ATTRIBUTE_NAME_DISPLAY_NAME]?.asS() ?: "",
      bio = entityValues[ATTRIBUTE_NAME_BIO]?.asS() ?: "",
      email = entityValues[ATTRIBUTE_NAME_EMAIL]!!.asS(),
      password = entityValues[ATTRIBUTE_NAME_PASSWORD]!!.asS()
    )
  }
}