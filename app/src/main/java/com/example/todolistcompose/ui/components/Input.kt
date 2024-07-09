package com.example.todolistcompose.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistcompose.ui.theme.Gray100
import com.example.todolistcompose.ui.theme.Gray300
import com.example.todolistcompose.ui.theme.Gray500
import com.example.todolistcompose.ui.theme.Gray700
import com.example.todolistcompose.ui.theme.PurpleDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputAddTask(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  onKeyboardActionDone: () -> Unit
) {
  val interactionSource = remember { MutableInteractionSource() }

  val colors = TextFieldDefaults.outlinedTextFieldColors(
    containerColor = Gray500,
    focusedBorderColor = PurpleDark,
    unfocusedBorderColor = Gray700,
  )

  BasicTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier
      .heightIn(54.dp)
      .fillMaxWidth(),
    interactionSource = interactionSource,
    enabled = true,
    singleLine = true,
    cursorBrush = SolidColor(Color.White),
    textStyle = MaterialTheme.typography.bodyLarge.copy(
      color = Gray100
    ),
    keyboardOptions = KeyboardOptions(
      capitalization = KeyboardCapitalization.Sentences,
      keyboardType = KeyboardType.Text,
      imeAction = ImeAction.Done,
    ),
    keyboardActions = KeyboardActions(
      onDone = { onKeyboardActionDone() }
    )
  ) {
    TextFieldDefaults.OutlinedTextFieldDecorationBox(
      value = value,
      innerTextField = it,
      enabled = true,
      singleLine = true,
      interactionSource = interactionSource,
      visualTransformation = VisualTransformation.None,
      placeholder = {
        Text(
          text = "Adicione uma nova tarefa",
          style = MaterialTheme.typography.bodyLarge,
          color = Gray300,
        )
      },
      container = {
        TextFieldDefaults.OutlinedBorderContainerBox(
          enabled = true,
          isError = false,
          interactionSource = interactionSource,
          colors = colors,
          shape = RoundedCornerShape(6.dp),
          focusedBorderThickness = 1.dp,
          unfocusedBorderThickness = 1.dp,
        )
      },
      colors = colors,
    )
  }
}

@Preview
@Composable
private fun InputPreview() {
  InputAddTask(value = "", onValueChange = {}, onKeyboardActionDone = {})
}
