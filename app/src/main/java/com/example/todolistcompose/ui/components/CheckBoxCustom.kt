package com.example.todolistcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistcompose.R
import com.example.todolistcompose.ui.theme.Blue
import com.example.todolistcompose.ui.theme.Gray100
import com.example.todolistcompose.ui.theme.Gray300
import com.example.todolistcompose.ui.theme.PurpleDark

@Composable
fun CheckBoxCustom(
  modifier: Modifier = Modifier,
  checked: Boolean = false,
  onCheckedChange: (Boolean) -> Unit,
  label: String? = null,
) {
  val shape = RoundedCornerShape(999.dp)
  val backgroundColor = if (!checked) Color.Transparent else PurpleDark
  val borderColor = if (!checked) Blue else PurpleDark
  val textDecoration = if (!checked) TextDecoration.None else TextDecoration.LineThrough
  val textColor = if (!checked) Gray100 else Gray300

  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Box(
      modifier = Modifier
          .clickable { onCheckedChange(!checked) }
          .size(17.45.dp)
          .clip(shape)
          .border(width = 2.dp, shape = shape, color = borderColor)
          .background(color = backgroundColor, shape = shape)
    ) {
      if (checked) {
        Image(
          painter = painterResource(R.drawable.checked),
          contentDescription = null,
          modifier = Modifier.align(Alignment.Center)
        )
      }
    }

    label?.let {
      Text(
        text = it,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = textColor, lineHeight = 19.6.sp, textDecoration = textDecoration
        ),
      )
    }
  }
}

@Preview
@Composable
private fun CheckBoxCustomPreview() {
  CheckBoxCustom(onCheckedChange = {})
}
