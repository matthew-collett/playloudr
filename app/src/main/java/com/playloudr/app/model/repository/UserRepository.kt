package com.playloudr.app.model.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import com.playloudr.app.model.entity.UserEntity
import com.playloudr.app.service.SessionManager
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_BIO
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_DISPLAY_NAME
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_EMAIL
import com.playloudr.app.util.Constants.DynamoDB.ATTRIBUTE_NAME_PROFILE_PICTURE_URL
import com.playloudr.app.util.Constants.DynamoDB.KEY_NAME_PK
import com.playloudr.app.util.Constants.DynamoDB.KEY_NAME_SK
import com.playloudr.app.util.Constants.DynamoDB.KEY_PREFIX_FOLLOWER
import com.playloudr.app.util.Constants.DynamoDB.KEY_PREFIX_FOLLOWING
import com.playloudr.app.util.Constants.DynamoDB.KEY_PREFIX_METADATA
import com.playloudr.app.util.Constants.DynamoDB.KEY_PREFIX_USER
import com.playloudr.app.util.Hasher

object UserRepository : AbstractRepository<UserEntity>() {

  suspend fun getUserProfile(username: String): UserEntity? {
    val primaryKey: Map<String, AttributeValue> = mutableMapOf(
      KEY_NAME_PK to AttributeValue.S(KEY_PREFIX_USER + username),
      KEY_NAME_SK to AttributeValue.S(KEY_PREFIX_METADATA + username)
    )
    val response: Map<String, AttributeValue>? = dynamoDbDao.getItem(primaryKey)
    return response?.let { builder(it) }
  }

  private suspend fun getUserPassword(username: String): String? {
    val primaryKey: Map<String, AttributeValue> = mutableMapOf(
      KEY_NAME_PK to AttributeValue.S(KEY_PREFIX_USER + username),
      KEY_NAME_SK to AttributeValue.S(KEY_PREFIX_METADATA + username)
    )
    val response: Map<String, AttributeValue>? = dynamoDbDao.getItem(primaryKey)
    return response?.get("Password")?.asS()
  }

  suspend fun searchUsers(searchText: String): List<UserEntity> {
    val filterExpression = "begins_with(PK, :pkSearchText) AND begins_with(SK, :skSearchText)"
    val expressionAttributeValues: Map<String, AttributeValue> = mapOf(
      ":pkSearchText" to AttributeValue.S("USER#$searchText"),
      ":skSearchText" to AttributeValue.S("METADATA#$searchText")
    )
    val response = dynamoDbDao.scan(filterExpression, expressionAttributeValues)
    return response?.map { builder(it) } ?: emptyList()
  }



  suspend fun getUserFollowing(username: String): List<String> {
    return dynamoShallowPrefixQuery(username, KEY_PREFIX_FOLLOWING)
  }
  suspend fun getUserFollowers(username: String): List<String> {
    return dynamoShallowPrefixQuery(username, KEY_PREFIX_FOLLOWER)
  }

  suspend fun isFollowing(currentUsername: String, username: String): Boolean {
    val attributes: Map<String, AttributeValue> = mutableMapOf(
      KEY_NAME_PK to AttributeValue.S(KEY_PREFIX_USER + currentUsername),
      KEY_NAME_SK to AttributeValue.S(KEY_PREFIX_FOLLOWING + username)
    )
    return dynamoDbDao.getItem(attributes) != null
  }

  suspend fun followUser(currentUsername: String, username: String) {
    val followingKey: Map<String, AttributeValue> = mutableMapOf(
      KEY_NAME_PK to AttributeValue.S(KEY_PREFIX_USER + currentUsername),
      KEY_NAME_SK to AttributeValue.S(KEY_PREFIX_FOLLOWING + username)
    )
    dynamoDbDao.putItem(followingKey)

    val followerKey: Map<String, AttributeValue> = mutableMapOf(
      KEY_NAME_PK to AttributeValue.S(KEY_PREFIX_USER + username),
      KEY_NAME_SK to AttributeValue.S(KEY_PREFIX_FOLLOWER + currentUsername)
    )
    dynamoDbDao.putItem(followerKey)
  }

  suspend fun unfollowUser(currentUsername: String, username: String) {
    val followingKey: Map<String, AttributeValue> = mutableMapOf(
      KEY_NAME_PK to AttributeValue.S(KEY_PREFIX_USER + currentUsername),
      KEY_NAME_SK to AttributeValue.S(KEY_PREFIX_FOLLOWING + username)
    )
    dynamoDbDao.deleteItem(followingKey)

    val followerKey: Map<String, AttributeValue> = mutableMapOf(
      KEY_NAME_PK to AttributeValue.S(KEY_PREFIX_USER + username),
      KEY_NAME_SK to AttributeValue.S(KEY_PREFIX_FOLLOWER + currentUsername)
    )
    dynamoDbDao.deleteItem(followerKey)
  }

  suspend fun updateProfilePicture(username: String, profilePictureUrl: String) {
    val key: Map<String, AttributeValue> = mutableMapOf(
      KEY_NAME_PK to AttributeValue.S(KEY_PREFIX_USER + username),
      KEY_NAME_SK to AttributeValue.S(KEY_PREFIX_METADATA + username)
    )
    val updatedValues = mapOf("ProfilePictureUrl" to AttributeValue.S(profilePictureUrl))
    dynamoDbDao.updateItem(key, updatedValues)
  }

  suspend fun signIn(username: String, password: String): Boolean {
    val isSuccess: Boolean = getUserPassword(username)?.let { Hasher.compare(password, it) } == true
    if (isSuccess) {
      SessionManager.loginUser(username)
    }
    return isSuccess
  }

  suspend fun signUp(username: String, password: String, email: String, bio: String?, displayName: String?) {
    val hashedPassword = Hasher.hash(password)
    val userAttributes: Map<String, AttributeValue> = mutableMapOf(
      KEY_NAME_PK to AttributeValue.S(KEY_PREFIX_USER + username),
      KEY_NAME_SK to AttributeValue.S(KEY_PREFIX_METADATA + username),
      "Password" to AttributeValue.S(hashedPassword),
      ATTRIBUTE_NAME_EMAIL to AttributeValue.S(email),
      ATTRIBUTE_NAME_PROFILE_PICTURE_URL to AttributeValue.S("https://i.stack.imgur.com/34AD2.jpg"),
      ATTRIBUTE_NAME_BIO to AttributeValue.S(bio?: ""),
      ATTRIBUTE_NAME_DISPLAY_NAME to AttributeValue.S(displayName?: "")
    )
    dynamoDbDao.putItem(userAttributes)
  }

  override fun builder(entityValues: Map<String, AttributeValue>): UserEntity {
    return UserEntity(
      username = resolveKeyName(entityValues[KEY_NAME_PK]!!.asS()),
      profilePictureUrl = entityValues[ATTRIBUTE_NAME_PROFILE_PICTURE_URL]!!.asS(),
      displayName = entityValues[ATTRIBUTE_NAME_DISPLAY_NAME]?.asS() ?: "",
      bio = entityValues[ATTRIBUTE_NAME_BIO]?.asS() ?: "",
      email = entityValues[ATTRIBUTE_NAME_EMAIL]!!.asS()
    )
  }
}