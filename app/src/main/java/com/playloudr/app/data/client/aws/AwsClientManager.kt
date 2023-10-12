package com.playloudr.app.data.client.aws

import aws.sdk.kotlin.services.cognitoidentity.CognitoIdentityClient
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.kms.KmsClient
import aws.sdk.kotlin.services.s3.S3Client
import aws.smithy.kotlin.runtime.client.SdkClient
import com.playloudr.app.data.client.aws.AwsServiceClient.CognitoServiceClient
import com.playloudr.app.data.client.aws.AwsServiceClient.DynamoDbServiceClient
import com.playloudr.app.data.client.aws.AwsServiceClient.KmsServiceClient
import com.playloudr.app.data.client.aws.AwsServiceClient.S3ServiceClient
import com.playloudr.app.data.client.config.ClientConfig
import com.playloudr.app.data.client.config.CognitoProvider
import java.util.concurrent.ConcurrentHashMap

class AwsClientManager(private val config: ClientConfig) {
  private val cognito: CognitoServiceClient = CognitoServiceClient(config)
  private val provider: CognitoProvider = CognitoProvider(config, getCognito().getClient())
  private val clientPool: ConcurrentHashMap<Class<*>, AwsClient<*>> = ConcurrentHashMap()
  val ok = getS3()

  fun getDynamoDb(): DynamoDbServiceClient {
    return clientPool.computeIfAbsent(DynamoDbServiceClient::class.java) {
      DynamoDbServiceClient(config, provider)
    }
  }

  fun getS3(): AwsClient<*> {
    return clientPool.computeIfAbsent(S3ServiceClient::class.java) {
      S3ServiceClient(config, provider)
    }
  }

  fun getKms(): AwsClient<*> {
    return clientPool.computeIfAbsent(KmsServiceClient::class.java) {
      KmsServiceClient(config, provider)
    }
  }

  private fun getCognito(): AwsClient<*> {
    return clientPool.computeIfAbsent(CognitoServiceClient::class.java) {
      CognitoServiceClient(config)
    }
  }
}