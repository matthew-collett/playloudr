package com.playloudr.app.model.repository

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import com.playloudr.app.model.entity.PostEntity
import com.playloudr.app.model.enum.PostType
import com.playloudr.app.util.Constants
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_ARTIST
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_AUDIO_URL
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_CAPTION
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_IMAGE_URL
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_PROFILE_PICTURE_URL
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_TITLE
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_TYPE
import com.playloudr.app.util.Constants.DynamoDB.KEY_NAME_PK
import com.playloudr.app.util.Constants.DynamoDB.KEY_NAME_SK
import com.playloudr.app.util.Constants.DynamoDB.KEY_PREFIX_POST
import com.playloudr.app.util.Constants.DynamoDB.KEY_PREFIX_USER
import com.playloudr.app.util.DateTimeUtils.resolveTimestamp
import java.time.Instant
import java.util.UUID

object PostRepository : AbstractRepository<PostEntity>() {
  private val userRepository: UserRepository = UserRepository

  suspend fun getUserPosts(username: String): List<PostEntity> {
    return dynamoPrefixQuery(username, KEY_PREFIX_POST)
  }

  suspend fun getUserPost(username: String, timestamp: Instant): PostEntity? {
    return dynamoGetItem(username, KEY_PREFIX_POST + timestamp)
  }

  suspend fun getFeedPosts(username: String): List<PostEntity> {
    val userFollowing: List<String> = userRepository.getUserFollowing(username)
    return userFollowing
      .map { getUserPosts(it) }
      .flatten()
      .sortedByDescending { it.timestamp }
  }

  suspend fun createUserPost(post: PostEntity) {
    val itemValues: Map<String, AttributeValue> = mutableMapOf(
      KEY_NAME_PK to AttributeValue.S(KEY_PREFIX_USER + post.username),
      KEY_NAME_SK to AttributeValue.S(KEY_PREFIX_POST + post.timestamp),
      ATTRIBUTE_NAME_TITLE to AttributeValue.S(post.title),
      ATTRIBUTE_NAME_ARTIST to AttributeValue.S(post.artist),
      ATTRIBUTE_NAME_CAPTION to AttributeValue.S(post.caption ?: ""),
      ATTRIBUTE_NAME_IMAGE_URL to AttributeValue.S(post.imageUrl)
    )
    dynamoDbDao.putItem(itemValues)
  }


  override fun builder(entityValues: Map<String, AttributeValue>): PostEntity {
    return PostEntity(
      username = resolveKeyName(entityValues[KEY_NAME_PK]!!.asS()),
      timestamp = resolveTimestamp(resolveKeyName(entityValues[KEY_NAME_SK]!!.asS())),
      title = entityValues[ATTRIBUTE_NAME_TITLE]!!.asS(),
      artist = entityValues[ATTRIBUTE_NAME_ARTIST]!!.asS(),
      caption = entityValues[ATTRIBUTE_NAME_CAPTION]?.asS() ?: "",
      imageUrl = entityValues[ATTRIBUTE_NAME_IMAGE_URL]!!.asS()
    )
  }
}