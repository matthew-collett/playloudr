import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueRequest
import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueResponse
import com.playloudr.app.model.dao.SecretsManagerDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SecretsManagerDaoTest {

  @Test
  fun testGetSecret() = runBlocking {
    // Create a mock SecretsManagerClient
    val mockClient = mock<SecretsManagerClient>()

    // Prepare the expected response
    val expectedSecretString = "mySecretValue"
    val mockResponse = GetSecretValueResponse {
      secretString = expectedSecretString
    }

    // When the getSecretValue method is called with any GetSecretValueRequest, return mockResponse
    whenever(mockClient.getSecretValue(any<GetSecretValueRequest>())).thenReturn(mockResponse)

    // Create an instance of your DAO with the mock client
    val dao = SecretsManagerDao().apply {
      setTestClient(mockClient)
    }

    // Perform the operation
    val secretValue = dao.getSecret("mySecretId")

    // Assert that the result is as expected
    Assert.assertEquals(expectedSecretString, secretValue)
  }
}
