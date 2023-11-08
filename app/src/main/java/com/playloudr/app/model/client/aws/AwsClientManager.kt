package com.playloudr.app.model.client.aws

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.kms.KmsClient
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.smithy.kotlin.runtime.client.SdkClient
import com.playloudr.app.model.client.aws.AwsServiceClient.DynamoDbServiceClient
import com.playloudr.app.model.client.aws.AwsServiceClient.KmsServiceClient
import com.playloudr.app.model.client.aws.AwsServiceClient.S3ServiceClient
import com.playloudr.app.model.client.aws.AwsServiceClient.SecretServiceClient
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

object AwsClientManager {
  private val clientMap: ConcurrentHashMap<KClass<out SdkClient>, AwsServiceClient<out SdkClient>> =
    ConcurrentHashMap()

  fun getDynamoDbClient(): AwsServiceClient<DynamoDbClient> {
    return clientMap.computeIfAbsent(DynamoDbClient::class) {
      DynamoDbServiceClient
    } as DynamoDbServiceClient
  }

  fun getS3Client(): AwsServiceClient<S3Client> {
    return clientMap.computeIfAbsent(S3Client::class) {
      S3ServiceClient
    } as S3ServiceClient
  }

  fun getKmsClient(): AwsServiceClient<KmsClient> {
    return clientMap.computeIfAbsent(KmsClient::class) {
      KmsServiceClient
    } as KmsServiceClient
  }


  fun getSecretsManagerClient(): AwsServiceClient<SecretsManagerClient> {
    return clientMap.computeIfAbsent(SecretsManagerClient::class) {
      SecretServiceClient
    } as SecretServiceClient
  }


  fun closeClients() {
    clientMap.values.forEach { client ->
      client.closeClient()
    }
  }
}