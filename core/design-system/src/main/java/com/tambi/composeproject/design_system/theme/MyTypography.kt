package com.tambi.composeproject.design_system.theme

import android.annotation.SuppressLint
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.tambi.composeproject.design_system.R

object MyTypography {
    // Font Variation
    private val Pretendard =
        FontFamily(
            Font(
                R.font.pretendard_bold,
                FontWeight.Bold,
                FontStyle.Normal,
            ),
            Font(
                R.font.pretendard_semibold,
                FontWeight.SemiBold,
                FontStyle.Normal,
            ),
            Font(
                R.font.pretendard_regular,
                FontWeight.Normal,
                FontStyle.Normal,
            ),
        )

    @SuppressLint("Deprecated")
    private val baseTextStyle =
        TextStyle(
            fontFamily = Pretendard,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
            lineHeight = 4.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            ),
        )

    val Bold: TextStyle = baseTextStyle.copy(fontWeight = FontWeight.Bold)
    val Bold32: TextStyle = Bold.copy(fontSize = 32.sp, lineHeight = 40.sp)
    val Bold24: TextStyle = Bold.copy(fontSize = 24.sp, lineHeight = 32.sp)
    val Bold16: TextStyle = Bold.copy(fontSize = 16.sp, lineHeight = 24.sp)

    val SemiBold: TextStyle = baseTextStyle.copy(fontWeight = FontWeight.SemiBold)
    val SemiBold24: TextStyle = SemiBold.copy(fontSize = 24.sp, lineHeight = 32.sp)
    val SemiBold22: TextStyle = SemiBold.copy(fontSize = 22.sp, lineHeight = 28.sp)
    val SemiBold20: TextStyle = SemiBold.copy(fontSize = 20.sp, lineHeight = 24.sp)
    val SemiBold16: TextStyle = SemiBold.copy(fontSize = 16.sp, lineHeight = 24.sp)
    val SemiBold14: TextStyle = SemiBold.copy(fontSize = 14.sp, lineHeight = 20.sp)
    val SemiBold12: TextStyle = SemiBold.copy(fontSize = 12.sp, lineHeight = 16.sp)

    val Regular: TextStyle = baseTextStyle.copy(fontWeight = FontWeight.Normal)
    val Regular16: TextStyle = Regular.copy(fontSize = 16.sp, lineHeight = 24.sp)
    val Regular15: TextStyle = Regular.copy(fontSize = 15.sp, lineHeight = 22.sp)
    val Regular14: TextStyle = Regular.copy(fontSize = 14.sp, lineHeight = 20.sp)
    val Regular13: TextStyle = Regular.copy(fontSize = 13.sp, lineHeight = 16.sp)
    val Regular12: TextStyle = Regular.copy(fontSize = 12.sp, lineHeight = 16.sp)
}
