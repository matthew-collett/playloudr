package com.playloudr.app.model.dao

import android.util.Log
import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueRequest
import aws.sdk.kotlin.services.secretsmanager.model.SecretsManagerException
import java.io.IOException

class SecretsManagerDao : AbstractDao<SecretsManagerClient>(SecretsManagerClient::class) {
  companion object { const val TAG: String = "[SecretsManager Error]: " }

  suspend fun getSecret(secretName: String): String? {
    val request = GetSecretValueRequest {
      secretId = secretName
    }
    return try {
      val response = getClient().getSecretValue(request)
      response.secretString
    } catch (e: SecretsManagerException) {
      Log.w(TAG, "Unable to get secret from ${e.message}", e)
      throw e
    }
  }
}
