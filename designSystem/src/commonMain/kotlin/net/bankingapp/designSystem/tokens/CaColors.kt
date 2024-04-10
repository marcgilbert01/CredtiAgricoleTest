package net.bankingapp.designSystem.tokens

import androidx.compose.ui.graphics.Color

data class CaColors (
    val primary: Color,
    val primaryVariant: Color,
    val secondary: Color,
    val secondaryVariant: Color,
    val tertiary: Color,
    val background: Color,
    val surface: Color
)


val caColors = CaColors(
    primary = Color.DarkGray,
    primaryVariant = Color(0xFF3700B3),
    secondary = Color.LightGray,
    secondaryVariant = Color(0xFF018786),
    tertiary = Color.Blue,
    background = Color.White,
    surface = Color(0xFFE0E0E0)
)