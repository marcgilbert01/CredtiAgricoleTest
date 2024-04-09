package net.bankingapp.designSystem.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class CaTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val subtitle1: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
)

val caTypography = CaTypography(
    h1 = TextStyle(
        fontSize = 36.sp,
        letterSpacing = 0.015.sp,
        lineHeight = 112.sp,
        fontWeight = FontWeight.Bold
    ),
    h2 = TextStyle(
        fontSize = 24.sp,
        letterSpacing = 0.015.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Bold
    ),
    h3 = TextStyle(
        fontSize = 16.sp,
        letterSpacing = 0.015.sp,
        lineHeight = 72.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Gray
    ),
    subtitle1 = TextStyle(
        fontSize = 16.sp,
        letterSpacing = 0.015.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal,
    ),
    body1 = TextStyle(
        fontSize = 16.sp,
        letterSpacing = 0.015.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
        color = caColors.primary
    ),
    body2 = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 0.015.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Bold,
        color = caColors.secondary
    ),
)