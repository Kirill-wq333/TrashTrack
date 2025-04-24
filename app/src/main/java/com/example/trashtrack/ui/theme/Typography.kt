package com.example.trashtrack.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.trashtrack.R

private val roboto = FontFamily(
    Font(R.font.roboto_condensed_regular, FontWeight.Normal),
    Font(R.font.roboto_condensed_italic, FontWeight.Thin),
    Font(R.font.roboto_condensed_bold, FontWeight.Bold),
    Font(R.font.roboto_condensed_extrabold, FontWeight.ExtraBold),
    Font(R.font.roboto_condensed_black, FontWeight.Black)
)

private val manrope = FontFamily(
    Font(R.font.manrope_regular, FontWeight.Normal),
    Font(R.font.manrope_bold, FontWeight.Bold),
    Font(R.font.manrope_extrabold, FontWeight.ExtraBold)
)

private val unbounded = FontFamily(
    Font(R.font.unbounded_black, FontWeight.Black)
)

@Stable
private fun getLetterSpacing(fontSize: Int): Double =
    if (fontSize >= 16) 0.018 else 0.011

val TTTypography: Typography
    @Composable
    get() = Typography(
        displayMedium = TextStyle(
            fontSize = 36.sp,
            lineHeight = 45.sp,
            fontFamily = unbounded,
            fontWeight = FontWeight.Black,
            letterSpacing = getLetterSpacing(36).sp
        ),
        displaySmall = TextStyle(
            fontSize = 32.sp,
            lineHeight = 37.5.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Black,
            letterSpacing = getLetterSpacing(32).sp
        ),
        headlineLarge = TextStyle(
            fontSize = 24.sp,
            lineHeight = 33.sp,
            fontFamily = manrope,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = getLetterSpacing(24).sp
        ),
        headlineMedium = TextStyle(
            fontSize = 24.sp,
            lineHeight = 28.1.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Thin,
            letterSpacing = getLetterSpacing(24).sp
        ),
        headlineSmall = TextStyle(
            fontSize = 20.sp,
            lineHeight = 23.4.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Black,
            letterSpacing = getLetterSpacing(20).sp
        ),
        titleLarge = TextStyle(
            fontSize = 16.sp,
            lineHeight = 21.9.sp,
            fontFamily = manrope,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = getLetterSpacing(16).sp
        ),
        titleMedium = TextStyle(
            fontSize = 16.sp,
            lineHeight = 18.8.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            letterSpacing = getLetterSpacing(16).sp
        ),
        titleSmall = TextStyle(
            fontSize = 14.sp,
            lineHeight = 19.1.sp,
            fontFamily = manrope,
            fontWeight = FontWeight.Bold,
            letterSpacing = getLetterSpacing(14).sp
        ),
        bodyLarge = TextStyle(
            fontSize = 12.sp,
            lineHeight = 14.1.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = getLetterSpacing(12).sp
        ),
        bodyMedium = TextStyle(
            fontSize = 12.sp,
            lineHeight = 16.4.sp,
            fontFamily = manrope,
            fontWeight = FontWeight.Normal,
            letterSpacing = getLetterSpacing(12).sp
        ),
        bodySmall = TextStyle(
            fontSize = 8.sp,
            lineHeight = 9.4.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            letterSpacing = getLetterSpacing(8).sp
        ),
        labelLarge = TextStyle(
            fontSize = 6.sp,
            lineHeight = 7.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            letterSpacing = getLetterSpacing(6).sp
        ),
        labelMedium = TextStyle(
            fontSize = 4.sp,
            lineHeight = 4.7.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = getLetterSpacing(4).sp
        ),
        labelSmall = TextStyle(
            fontSize = 4.sp,
            lineHeight = 4.7.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            letterSpacing = getLetterSpacing(4).sp
        )
    )
