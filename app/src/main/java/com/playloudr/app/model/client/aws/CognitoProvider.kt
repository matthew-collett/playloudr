package com.playloudr.app.model.client.aws

import aws.sdk.kotlin.services.cognitoidentity.CognitoIdentityClient
import aws.sdk.kotlin.services.cognitoidentity.model.GetCredentialsForIdentityRequest
import aws.sdk.kotlin.services.cognitoidentity.model.GetIdRequest
import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import aws.smithy.kotlin.runtime.util.Attributes
import com.playloudr.app.model.client.config.ClientConfig

class CognitoProvider(private val config: ClientConfig) : CredentialsProvider {
  private val client = CognitoIdentityClient {
    region = config.aws.region
  }

  override suspend fun resolve(attributes: Attributes): Credentials {
    val id: String = getIdentityId()
    val response = client.getCredentialsForIdentity(GetCredentialsForIdentityRequest { identityId = id })
    val credentials = response.credentials
      ?: throw IllegalStateException("Failed to get credentials from Cognito")
    return Credentials(
      accessKeyId = credentials.accessKeyId!!,
      secretAccessKey = credentials.secretKey!!,
      sessionToken = credentials.sessionToken!!
    )
  }

  private suspend fun getIdentityId(): String {
    val response = client.getId(GetIdRequest { identityPoolId = config.aws.identityPoolId })
    return response.identityId ?: throw IllegalStateException("Identity ID is null")
  }
}


