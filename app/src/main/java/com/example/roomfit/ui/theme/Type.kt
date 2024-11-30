package com.example.roomfit.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.roomfit.R



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


val mulishBold = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold))

val pretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold))
val pretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold))
val pretendardRegular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal))

var buttonLarge = TextStyle(
    fontFamily = pretendardBold,
    fontSize = 45.sp
)

var buttonSmall = TextStyle(
    fontFamily = pretendardBold,
    fontWeight = FontWeight.Bold,
    fontSize = 40.sp
)

var bodyName = TextStyle(
    fontFamily = pretendardBold,
    fontWeight = FontWeight.Bold,
    fontSize = 48.sp
)

var bodyLarge = TextStyle(
    fontFamily = pretendardRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 40.sp
)

var bodyBold = TextStyle(
    fontFamily = pretendardBold,
    fontWeight = FontWeight.Bold,
    fontSize = 40.sp
)

var textfield = TextStyle(
    fontFamily = pretendardBold,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp
)

var textfield2 = TextStyle(
    fontFamily = pretendardRegular,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp
)

var bodyDetail = TextStyle(
    fontFamily = pretendardSemiBold,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp
)

var bodyWriting = TextStyle(
    fontFamily = pretendardRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp
)

var loginTitle = TextStyle(
    fontFamily = mulishBold,
    fontWeight = FontWeight.Bold,
    fontSize = 38.sp
)
var loginTitle2 = TextStyle(
    fontFamily = mulishBold,
    fontWeight = FontWeight.Bold,
    fontSize = 30.sp
)

var LoginButton = TextStyle(
    fontFamily = mulishBold,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp
)

var LoginInput = TextStyle(
    fontFamily = pretendardRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp
)

var UserInfo = TextStyle(
    fontFamily = pretendardRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)
