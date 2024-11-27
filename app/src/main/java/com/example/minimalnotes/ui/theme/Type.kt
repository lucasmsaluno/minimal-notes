package com.example.minimalnotes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.minimalnotes.R


val nunitoFontFamily = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_medium, FontWeight.Medium),
    Font(R.font.nunito_semi, FontWeight.SemiBold),
)

val playfairFontFamily = FontFamily(
    Font(R.font.playfair_regular, FontWeight.Normal),
    Font(R.font.playfair_medium, FontWeight.Medium),
    Font(R.font.playfair_semi, FontWeight.SemiBold),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)