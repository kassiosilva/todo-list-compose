package com.example.todolistcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistcompose.model.Task
import com.example.todolistcompose.ui.components.EmptyList
import com.example.todolistcompose.ui.components.Header
import com.example.todolistcompose.ui.components.InfoTasks
import com.example.todolistcompose.ui.components.InputAddTask
import com.example.todolistcompose.ui.theme.Blue
import com.example.todolistcompose.ui.theme.BlueDark
import com.example.todolistcompose.ui.theme.Gray100
import com.example.todolistcompose.ui.theme.Gray200
import com.example.todolistcompose.ui.theme.Gray300
import com.example.todolistcompose.ui.theme.Gray400
import com.example.todolistcompose.ui.theme.Gray500
import com.example.todolistcompose.ui.theme.Gray600
import com.example.todolistcompose.ui.theme.Gray700
import com.example.todolistcompose.ui.theme.Purple
import com.example.todolistcompose.ui.theme.PurpleDark
import com.example.todolistcompose.ui.theme.TodoListComposeTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      App()
    }
  }
}

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

@Composable
fun App() {
  TodoListComposeTheme(dynamicColor = false, darkTheme = false) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val tasks = remember { mutableStateListOf<Task>() }
    var taskValue by remember { mutableStateOf("") }

    fun handleTaskValue(value: String) {
      taskValue = value
    }

    fun handleSubmit(taskName: String) {
      if (taskName.isEmpty()) {
        Toast.makeText(context, "Digite o nome da task", Toast.LENGTH_SHORT).show()
        return
      }

      val taskId = UUID.randomUUID().toString()
      val taskItem = Task(id = taskId, label = taskName)

      tasks.add(taskItem)
      taskValue = ""
      focusManager.clearFocus()
    }

    fun handleRemove(task: Task) = tasks.remove(task)

    fun handleCheckedTask(checked: Boolean, id: String) {
      tasks.find { task -> task.id == id }?.let { task ->
        task.checked = checked
      }
    }

    val completedTasks = tasks.filter { task -> task.checked }.size

    Column(
      modifier = Modifier
          .fillMaxSize()
          .background(Gray600),
    ) {
      Header()

      Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .offset(y = (-32).dp)
      ) {
        InputAddTask(
          value = taskValue,
          onValueChange = { handleTaskValue(it) },
          modifier = Modifier.weight(1f),
          onKeyboardActionDone = { handleSubmit(taskValue) }
        )

        IconButton(
          onClick = { handleSubmit(taskValue) },
          modifier = Modifier
              .background(color = BlueDark, shape = RoundedCornerShape(6.dp))
              .size(54.dp)
        ) {
          Icon(
            painter = painterResource(R.drawable.plus),
            contentDescription = "Add",
            tint = Gray100,
          )
        }
      }

      InfoTasks(
        modifier = Modifier.padding(horizontal = 24.dp),
        createdTasks = tasks.size,
        completedTasks = completedTasks
      )

      Spacer(modifier = Modifier.height(20.dp))

      if (tasks.isEmpty()) {
        EmptyList()
      } else {
        LazyColumn(
          contentPadding = PaddingValues(horizontal = 24.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp),
          state = rememberLazyListState()
        ) {
          items(items = tasks, key = { task -> task.id }) { task ->
            TaskItem(
              label = task.label,
              checked = task.checked,
              onRemoveTask = { handleRemove(task) },
              onCheckedTask = { checked ->
                handleCheckedTask(checked = checked, id = task.id)
              }
            )
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
  Surface(modifier = Modifier.fillMaxSize()) {
    App()
  }
}
