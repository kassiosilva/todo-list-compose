package com.example.todolistcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistcompose.model.Task
import com.example.todolistcompose.ui.components.EmptyList
import com.example.todolistcompose.ui.components.Header
import com.example.todolistcompose.ui.components.InfoTasks
import com.example.todolistcompose.ui.components.InputAddTask
import com.example.todolistcompose.ui.components.TaskItem
import com.example.todolistcompose.ui.theme.BlueDark
import com.example.todolistcompose.ui.theme.Gray100
import com.example.todolistcompose.ui.theme.Gray600
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
            TaskItem(label = task.label,
              checked = task.checked,
              onRemoveTask = { handleRemove(task) },
              onCheckedTask = { checked ->
                handleCheckedTask(checked = checked, id = task.id)
              })
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
