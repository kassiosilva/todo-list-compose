package com.example.todolistcompose.ui.theme

import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.todolistcompose.R


val inter = FontFamily(
  Font(R.font.inter_regular, FontWeight.Normal),
  Font(R.font.inter_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
  titleLarge = TextStyle(
    fontFamily = inter,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
  ),
  bodyLarge = TextStyle(
    fontFamily = inter,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
  ),
  bodyMedium = TextStyle(
    fontFamily = inter,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
  ),
  bodySmall = TextStyle(
    fontFamily = inter,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
  )
)
