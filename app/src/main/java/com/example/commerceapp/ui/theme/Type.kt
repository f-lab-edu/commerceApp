package com.example.commerceapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val HeadlineSmall = TextStyle(
    fontSize = 24.sp,
    lineHeight = 32.sp,
    fontWeight = FontWeight.ExtraBold
)
val titleLarge = TextStyle(
    fontSize = 22.sp,
    lineHeight = 28.sp,
    fontWeight = FontWeight.Bold
)
val titleMedium = TextStyle(
    fontSize = 16.sp,
    lineHeight = 24.sp,
    fontWeight = FontWeight.Bold
)
val titleSmall = TextStyle(
    fontSize = 14.sp,
    lineHeight = 20.sp,
    fontWeight = FontWeight.Bold
)

val bodyLarge = TextStyle(
    fontSize = 22.sp,
    lineHeight = 28.sp,
    fontWeight = FontWeight.Normal
)
val bodyMedium = TextStyle(
    fontSize = 16.sp,
    lineHeight = 24.sp,
    fontWeight = FontWeight.Normal
)
val bodySmall = TextStyle(
    fontSize = 14.sp,
    lineHeight = 20.sp,
    fontWeight = FontWeight.Normal
)