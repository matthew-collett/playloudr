package com.playloudr.app.model.repository

import aws.sdk.kotlin.services.dynamodb.model.QueryResponse
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.model.entities.UserEntity

class PostRepository(user: UserEntity) : AbstractRepository<QueryResponse, PostEntity>() {
  override fun construct(): Map<String, Any> {
    TODO("Not yet implemented")
  }

  override suspend fun fetch(payload: Map<String, Any>): QueryResponse {
    TODO("Not yet implemented")
  }

  override fun process(response: QueryResponse): PostEntity {
    TODO("Not yet implemented")
  }

}