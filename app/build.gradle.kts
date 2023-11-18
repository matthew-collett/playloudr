plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
}

android {
  namespace = "com.playloudr.app"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.playloudr.app"
    minSdk = 26
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
//    jvmTarget = "1.8"
    jvmTarget = "17"
    freeCompilerArgs = listOf("-Xopt-in=kotlin.Experimental")
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.4.3"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
  implementation("androidx.activity:activity-compose:1.8.0")
  implementation(platform("androidx.compose:compose-bom:2023.03.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("aws.sdk.kotlin:s3:0.25.0-beta")
  implementation("androidx.compose.material:material:1.5.4")
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("aws.sdk.kotlin:dynamodb:0.25.0-beta")
  implementation("aws.sdk.kotlin:secretsmanager:0.25.0-beta")
  implementation("io.coil-kt:coil-compose:1.3.2")
  implementation("aws.sdk.kotlin:kms:0.25.0-beta")
  implementation("aws.sdk.kotlin:cognitoidentity:0.25.0-beta")
  implementation("com.typesafe:config:1.4.1")
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.navigation:navigation-compose:2.7.5")
  implementation("com.google.dagger:hilt-android:2.48")
  implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
  implementation("org.slf4j:slf4j-api:1.7.30")
  implementation("org.slf4j:slf4j-log4j12:1.7.30")
  testImplementation("junit:junit:4.13.2")
  testImplementation("org.mockito:mockito-core:4.0.0")
  testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
}