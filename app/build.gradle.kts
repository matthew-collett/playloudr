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
  implementation("aws.sdk.kotlin:s3:1.0.1") // already existing
  implementation("aws.sdk.kotlin:dynamodb:1.0.1") // already existing
  implementation("aws.sdk.kotlin:secretsmanager:1.0.1") // align this version with others
  implementation("aws.sdk.kotlin:kms:1.0.1") // already existing
  implementation("aws.sdk.kotlin:cognitoidentity:1.0.1") // already existing

  // Core runtime and other utilities (use the latest release date consistent with your SDK version)
  implementation("aws.sdk.kotlin:aws-core-jvm:1.0.1")
  implementation("aws.sdk.kotlin:aws-http-jvm:1.0.1")
  implementation("aws.smithy.kotlin:http-client-engine-okhttp:1.0.1")
  implementation("aws.smithy.kotlin:http-client-engine-crt:1.0.1")

  implementation("androidx.core:core-ktx:1.12.0")
  implementation("org.mindrot:jbcrypt:0.4")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
  implementation("androidx.activity:activity-compose:1.8.1")
  implementation(platform("androidx.compose:compose-bom:2023.03.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material:material:1.5.4")
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("io.coil-kt:coil-compose:1.3.2")
  implementation("com.typesafe:config:1.4.1")
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.navigation:navigation-compose:2.7.5")
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
}