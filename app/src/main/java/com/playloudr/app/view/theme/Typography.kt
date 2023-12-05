package com.playloudr.app.view.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.playloudr.app.R


val ModulusMediumFontFamily = FontFamily(Font(R.font.modulus_medium))
val RudaFontFamily = FontFamily(Font(R.font.ruda))

val CustomTypography = Typography(
  defaultFontFamily = RudaFontFamily,
  h1 = TextStyle(
    fontFamily = Modulus,
    fontWeight = FontWeight.Bold,
    fontSize = 30.sp
  ),
  h2 = TextStyle(fontFamily = ModulusMediumFontFamily),
  body1 = TextStyle(fontFamily = RudaFontFamily)
)
