package com.playloudr.app.util

object Constants {
  object DynamoDB {
    const val KEY_NAME_PK = "PK"
    const val KEY_NAME_SK = "SK"
    const val KEY_PREFIX_USER = "USER#"
    const val KEY_PREFIX_METADATA = "METADATA#"
    const val KEY_PREFIX_FOLLOWING = "FOLLOWING#"
    const val KEY_PREFIX_POST = "POST#"
    const val ATTRIBUTE_NAME_PROFILE_PICTURE_URL = "ProfilePictureUrl"
    const val ATTRIBUTE_NAME_DISPLAY_NAME = "DisplayName"
    const val ATTRIBUTE_NAME_BIO = "Bio"
    const val ATTRIBUTE_NAME_EMAIL = "Email"
    const val ATTRIBUTE_NAME_PASSWORD = "Password"
    const val ATTRIBUTE_NAME_TITLE = "Title"
    const val ATTRIBUTE_NAME_CAPTION = "Caption"
    const val ATTRIBUTE_NAME_IMAGE_URL = "ImageUrl"
    const val ATTRIBUTE_NAME_AUDIO_URL = "AudioUrl"
    const val ATTRIBUTE_NAME_ARTIST = "Artist"
    const val ATTRIBUTE_NAME_TYPE = "Type"
  }

  object ErrorMessages {
    const val ERROR_INVALID_INPUT = "Invalid input"
    const val ERROR_NETWORK = "Network error"
    // ... Other error messages
  }
}
