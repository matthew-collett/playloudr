package com.playloudr.app.model.repository
//
//import android.util.Log
//import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
//import com.playloudr.app.model.dao.SecretsManagerDao
//import com.playloudr.app.model.entity.SpotifyAccessTokenResponse
//import com.playloudr.app.model.entity.SpotifySearchResponse
//import com.playloudr.app.model.repository.SpotifyRepository.RetrofitInstance.retrofit
//import retrofit2.Retrofit
//import retrofit2.Response
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.Field
//import retrofit2.http.FormUrlEncoded
//import retrofit2.http.GET
//import retrofit2.http.Header
//import retrofit2.http.POST
//import retrofit2.http.Query
//import java.util.Base64
//
//
//object SpotifyRepository : AbstractRepository<SpotifyAccessTokenResponse>() {
//  // HTTP client
//  object RetrofitInstance {
//    val retrofit: Retrofit = Retrofit.Builder()
//      .baseUrl("https://accounts.spotify.com/")
//      .addConverterFactory(GsonConverterFactory.create())
//      .build()
//  }
//
//  private val secretsManagerDao = SecretsManagerDao()
//  private val spotifyService: SpotifyService by lazy {
//    retrofit.create(SpotifyService::class.java)
//  }
//  //TODO: Might need to change how AbstractRepository is structured
//  // this is just a temp builder
//  override fun builder(entityValues: Map<String, AttributeValue>): SpotifyAccessTokenResponse {
//    return SpotifyAccessTokenResponse("token", "tokentype", 10);
//  }
//
//  // This interface can be put into its own file under a network package inside the repository package
//  //com.playloudr.app
//  //└── repository
//  //    ├── SpotifyRepository.kt
//  //    └── network
//  //        └── SpotifyApiService.kt
//  interface SpotifyService {
//    @GET("search")
//      suspend fun searchSong(
//        @Header("Authorization") authHeader: String,
//        @Query("q") songName: String,
//        @Query("type") type: String = "track",
//        @Query("limit") limit: Int = 1
//      ): SpotifySearchResponse
//
//    // Function to get the access token
//    @POST("api/token")
//    @FormUrlEncoded
//    suspend fun getAccessToken(
//      @Header("Authorization") authHeader: String,
//      @Field("grant_type") grantType: String
//    ): Response<SpotifyAccessTokenResponse>
//  }
//
//  suspend fun searchSong(songName: String): String? {
//    val accessToken  = getSpotifyAccessToken() ?: return null
//    val authHeader = "Bearer $accessToken"
//
//    return try{
//      val response = retrofit.create(SpotifyService::class.java)
//        .searchSong(authHeader, songName)
//      response.albums.items.firstOrNull()?.images?.firstOrNull()?.url
//    } catch(e: Exception) {
//      Log.e("SpotifyRepository", "Failed to get image url for {$songName}")
//      return e.message
//    }
//  }
//
//  suspend fun getSpotifyAccessToken(): String? {
//    val clientId = secretsManagerDao.getSecret("client_id")
//    val clientSecret = secretsManagerDao.getSecret("client_secret")
//
//    if (clientId == null || clientSecret == null) {
//      Log.e("SpotifyRepository", "Client ID or Client Secret is null")
//      return null
//    }
//
//    // Encode the client ID and secret in base64 as required for Spotify's API
//    val authHeader = Base64.getEncoder().encodeToString("$clientId:$clientSecret".toByteArray())
//
//    return try {
//      val response = spotifyService.getAccessToken(authHeader, "client_credentials")
//      if (response.isSuccessful) {
//        response.body()?.accessToken
//      } else {
//        Log.e("SpotifyRepository", "Failed to get Spotify access token: ${response.errorBody()?.string()}")
//        return null
//      }
//    } catch (e: Exception) {
//      Log.e("SpotifyRepository", "Exception when getting Spotify access token", e)
//      return null
//    }
//  }
//}
