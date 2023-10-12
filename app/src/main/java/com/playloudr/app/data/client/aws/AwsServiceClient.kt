package com.playloudr.app.data.client.aws

import aws.sdk.kotlin.services.cognitoidentity.CognitoIdentityClient
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.kms.KmsClient
import aws.sdk.kotlin.services.s3.S3Client
import com.playloudr.app.data.client.config.ClientConfig
import com.playloudr.app.data.client.config.CognitoProvider

sealed class AwsServiceClient {

  class DynamoDbServiceClient(
    private val config: ClientConfig,
    private val provider: CognitoProvider
  ) : AwsClient<DynamoDbClient>() {
    override fun getClient(): DynamoDbClient {
      return DynamoDbClient {
        region = config.aws.region
        credentialsProvider = provider
      }
    }
  }

  class S3ServiceClient(
    private val config: ClientConfig,
    private val provider: CognitoProvider
  ) : AwsClient<S3Client>() {
    override fun getClient(): S3Client {
      return S3Client {
        region = config.aws.region
        credentialsProvider = provider
      }
    }
  }

  class KmsServiceClient(
    private val config: ClientConfig,
    private val provider: CognitoProvider
  ) : AwsClient<KmsClient>() {
    override fun getClient(): KmsClient {
      return KmsClient {
        region = config.aws.region
        credentialsProvider = provider
      }
    }
  }

  class CognitoServiceClient(private val config: ClientConfig) : AwsClient<CognitoIdentityClient>() {
    override fun getClient(): CognitoIdentityClient {
      return CognitoIdentityClient {
        region = config.aws.region
      }
    }
  }
}