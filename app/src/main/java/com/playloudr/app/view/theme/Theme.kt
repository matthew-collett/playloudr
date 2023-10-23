package com.playloudr.app.view.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.playloudr.app.R

val Ruda = FontFamily(
  Font(R.font.ruda),
  Font(R.font.ruda, FontWeight.Bold),
  Font(R.font.ruda, FontWeight.Light)
)

val Modulus = FontFamily(
  Font(R.font.modulus_medium),
  Font(R.font.modulus_bold, FontWeight.Bold),
  Font(R.font.modulus, FontWeight.Light)
)

private val DarkColorPalette = darkColors(
  primary = Color.White, // Replace with your own color
  secondary = Color.Gray, // Replace with your own color
  // other colors if needed
)

private val LightColorPalette = lightColors(
  primary = Color.White, // Replace with your own color
  secondary = Color.Black, // Replace with your own color
  // other colors if needed
)

  /* Other default colors to override
background = Color(0xFFFFFBFE),
surface = Color(0xFFFFFBFE),
onPrimary = Color.White,
onSecondary = Color.White,
onTertiary = Color.White,
onBackground = Color(0xFF1C1B1F),
onSurface = Color(0xFF1C1B1F),
*/


@Composable
fun PlayloudrTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  // Choose the appropriate color palette based on the theme
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  val context = LocalContext.current
  val view = LocalView.current
  val window = (context as Activity).window

  // Side effect for setting status bar color and light status bars
  if (!view.isInEditMode) {
    SideEffect {
      window.statusBarColor = colors.primary.toArgb()
      WindowCompat.getInsetsController(window, view)?.isAppearanceLightStatusBars = !darkTheme
    }
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    content = content
  )
}