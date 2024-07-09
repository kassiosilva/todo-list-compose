package com.example.todolistcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistcompose.R
import com.example.todolistcompose.ui.theme.Gray300
import com.example.todolistcompose.ui.theme.Gray400
import com.example.todolistcompose.ui.theme.Gray500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
  modifier: Modifier = Modifier,
  checked: Boolean,
  label: String,
  onCheckedTask: (Boolean) -> Unit,
  onRemoveTask: () -> Unit
) {
  val borderColor = if (!checked) Gray400 else Gray500

  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    modifier = modifier
      .fillMaxWidth()
      .background(color = Gray500, shape = RoundedCornerShape(8.dp))
      .border(
        width = 1.dp, color = borderColor, shape = RoundedCornerShape(8.dp)
      )
      .padding(start = 12.dp, end = 8.dp, top = 12.dp, bottom = 12.dp)
  ) {

    CheckBoxCustom(
      checked = checked,
      onCheckedChange = { isChecked -> onCheckedTask(isChecked) },
      label = label,
      modifier = Modifier.weight(1f)
    )

    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
      IconButton(
        onClick = onRemoveTask, modifier = Modifier.size(32.dp)
      ) {
        Icon(
          painter = painterResource(id = R.drawable.trash),
          contentDescription = null,
          tint = Gray300
        )
      }
    }
  }
}

@Preview
@Composable
private fun TaskItemPreview() {
  TaskItem(checked = false, label = "Exemple de tarefa", onCheckedTask = {}, onRemoveTask = {})
}
