package com.playloudr.app.data.client.config

import aws.sdk.kotlin.services.cognitoidentity.CognitoIdentityClient
import aws.sdk.kotlin.services.cognitoidentity.model.GetCredentialsForIdentityRequest
import aws.sdk.kotlin.services.cognitoidentity.model.GetIdRequest
import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import aws.smithy.kotlin.runtime.util.Attributes

class CognitoProvider(private val config: ClientConfig,
  private val client: CognitoIdentityClient
) : CredentialsProvider {

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


