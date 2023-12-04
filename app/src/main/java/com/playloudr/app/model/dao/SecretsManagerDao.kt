package com.playloudr.app.model.dao

import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueRequest
import com.playloudr.app.model.client.aws.AwsClientManager

class SecretsManagerDao {
  private val client: SecretsManagerClient = AwsClientManager.getButtCFace3()
  suspend fun getSecret(secretName: String): String? {
    val request = GetSecretValueRequest {
      secretId = secretName
    }
    val response = client.getSecretValue(request)
    return response.secretString
  }
}
