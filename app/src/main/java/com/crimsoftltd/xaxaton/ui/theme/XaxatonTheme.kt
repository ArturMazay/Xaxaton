package com.crimsoftltd.xaxaton.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val fitness_caption = Color.DarkGray
val fitness_divider_color = Color.LightGray
private val fitness_rose = Color(0xFFE30425)
private val fitness_withe = Color.White
private val fitness_purple_700 = Color(0xFF720D5D)
private val fitness_purple_800 = Color(0xFF5D1049)
private val fitness_purple_900 = Color(0xFF4E0D3A)

val craneColors = lightColors(
    primary = fitness_purple_800,
    secondary = fitness_rose,
    surface = fitness_purple_900,
    onSurface = fitness_withe,
    primaryVariant = fitness_purple_700
)

val BottomSheetShape = RoundedCornerShape(
    topStart = 20.dp,
    topEnd = 20.dp,
    bottomStart = 0.dp,
    bottomEnd = 0.dp
)

@Composable
fun XaxatonTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = craneColors, typography = fitnessTypography) {
        content()
    }
}