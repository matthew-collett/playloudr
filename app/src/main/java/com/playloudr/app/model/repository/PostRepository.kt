package com.playloudr.app.model.repository

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.model.enums.PostType
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
import com.playloudr.app.util.DateTimeUtils
import com.playloudr.app.util.DateTimeUtils.resolveTimestamp
import java.time.Instant

object PostRepository : AbstractRepository<PostEntity>() {
  private val userRepository: UserRepository = UserRepository

  suspend fun getUserPosts(username: String): List<PostEntity> {
    return prefixQuery(username, KEY_PREFIX_POST)
  }

  suspend fun getFeedPosts(username: String): List<PostEntity> {
    val userFollowing: List<String> = userRepository.getUserFollowing(username)
    return userFollowing
      .map { getUserPosts(it) }
      .flatten()
      .sortedByDescending { it.timestamp }
  }

  override fun builder(entityValues: Map<String, AttributeValue>): PostEntity {
    return PostEntity(
      username = resolveKeyName(entityValues[KEY_NAME_PK]!!.asS()),
      timestamp = resolveTimestamp(resolveKeyName(entityValues[KEY_NAME_SK]!!.asS())),
      title = entityValues[ATTRIBUTE_NAME_TITLE]!!.asS(),
      artist = entityValues[ATTRIBUTE_NAME_ARTIST]!!.asS(),
      caption = entityValues[ATTRIBUTE_NAME_CAPTION]?.asS() ?: "",
      imageUrl = entityValues[ATTRIBUTE_NAME_IMAGE_URL]!!.asS(),
      audioUrl = entityValues[ATTRIBUTE_NAME_AUDIO_URL]!!.asS(),
      postType = PostType.fromString(entityValues[ATTRIBUTE_NAME_TYPE]!!.asS()),
      profilePictureUrl = entityValues[ATTRIBUTE_NAME_PROFILE_PICTURE_URL]!!.asS()
    )
  }
}