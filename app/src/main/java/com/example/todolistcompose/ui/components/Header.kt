package com.example.todolistcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistcompose.R
import com.example.todolistcompose.ui.theme.Gray700

@Composable
fun Header(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(173.dp)
      .background(Gray700)
  ) {
    Image(
      painter = painterResource(R.drawable.logo),
      contentDescription = null,
      modifier = Modifier.align(Alignment.Center)
    )
  }
}

@Preview
@Composable
private fun HeaderPreview() {
  Header()
}
