package com.playloudr.app.model.dao

import android.util.Log
import aws.sdk.kotlin.services.dynamodb.model.DynamoDbException
import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueRequest
import aws.sdk.kotlin.services.secretsmanager.model.SecretsManagerException
import com.playloudr.app.model.client.aws.AwsServiceClient
import java.io.IOException

class SecretsManagerDao : AbstractDao<SecretsManagerClient>(SecretsManagerClient::class) {
  companion object {
    const val TAG: String = "[SecretsManager Error]: "
  }

  suspend fun getSecret(secretName: String?): String? {
    val valueRequest = GetSecretValueRequest {
      secretId = secretName
    }
    return try {
      val client = getCurrentClient()
      val response = client.getSecretValue(valueRequest)
      response.secretString
    }catch (e: SecretsManagerException) {
      Log.w(TAG, "Unable to get secret from ${e.message}", e)
      throw e
    } catch (e: IOException) {
      Log.e(TAG, "IO Exception ${e.message}", e)
      throw e
    }
  }

  // All below is for unit test
  var testClient: SecretsManagerClient? = null
    private set

  fun setTestClient(mockClient: SecretsManagerClient?) {
    this.testClient = mockClient
  }

  private fun getCurrentClient(): SecretsManagerClient {
    return testClient ?: super.getClient()
  }

}
