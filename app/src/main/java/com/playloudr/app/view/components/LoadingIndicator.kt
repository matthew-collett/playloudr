package com.playloudr.app.view.components


import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.playloudr.app.view.theme.PLLogoColor

@Composable
fun LoadingIndicator(
  modifier: Modifier = Modifier,
  dotSize: Dp = 24.dp,
  color: Color = PLLogoColor,
  spacing: Dp = 8.dp
) {
  val infiniteTransition = rememberInfiniteTransition(label = "")
  Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
      (0..2).forEach { index ->
        val scale by infiniteTransition.animateFloat(
          initialValue = 0.3f,
          targetValue = 1f,
          animationSpec = infiniteRepeatable(
            animation = keyframes {
              durationMillis = 1500
              0.3f at 0 with LinearOutSlowInEasing
              1f at (100 + (index * 100)) with LinearOutSlowInEasing
              0.3f at (300 + (index * 100)) with LinearOutSlowInEasing
            },
            repeatMode = RepeatMode.Restart
          ), label = ""
        )

        DotIndicator(
          size = dotSize,
          color = color,
          scale = scale
        )
        if (index < 2) Spacer(modifier = Modifier.width(spacing))
      }
    }
  }
}

@Composable
fun DotIndicator(size: Dp, color: Color, scale: Float) {
  Box(
    modifier = Modifier
      .size(size)
      .scale(scale)
      .background(color, CircleShape)
  )
}

