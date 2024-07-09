package com.example.todolistcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
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

//    Row(
//      horizontalArrangement = Arrangement.spacedBy(4.dp),
//      modifier = Modifier
//        .fillMaxWidth()
//        .align(Alignment.BottomCenter)
//        .padding(horizontal = 24.dp)
//        .offset(y = 24.dp)
//    ) {
//      InputAddTask(
//        value = taskValue,
//        onValueChange = { handleTaskValue(it) },
//        modifier = Modifier.weight(1f),
//        onKeyboardActionDone = { handleSubmit() }
//      )
//
//      IconButton(
//        onClick = { handleSubmit() },
//        modifier = Modifier
//          .background(color = BlueDark, shape = RoundedCornerShape(6.dp))
//          .size(54.dp)
//      ) {
//        Icon(
//          painter = painterResource(R.drawable.plus),
//          contentDescription = "Add",
//          tint = Gray100,
//        )
//      }
//    }
  }
}

@Preview
@Composable
private fun HeaderPreview() {
  Header()
}
