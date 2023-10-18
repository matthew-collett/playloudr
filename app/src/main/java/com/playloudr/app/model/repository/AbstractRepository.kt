package com.playloudr.app.model.repository

import com.playloudr.app.model.client.aws.AwsClientManager

abstract class AbstractRepository<R, D> {

  abstract fun construct(): Map<String, Any>

  abstract suspend fun fetch(payload: Map<String, Any>): R

  abstract fun process(response: R): D

  suspend fun pull(): D {
    val payload = construct()
    val response = fetch(payload)
    return process(response = response)
  }

  fun getClientManager(): AwsClientManager {
    return AwsClientManager
  }
}