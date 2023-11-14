package com.example.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.CreationExtras
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputAddTask(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
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
            imeAction = ImeAction.Done
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
                    color = Gray300
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

@Composable
fun Header(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
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

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp)
                .offset(y = 24.dp)
        ) {
            InputAddTask(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = { /*TODO*/ },
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
    }
}

@Composable
fun InfoTasks(modifier: Modifier = Modifier, createdTasks: Int = 0, completedTasks: Int = 0) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Criadas",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Blue,
                    fontWeight = FontWeight.Bold
                ),
            )

            Box(
                modifier = Modifier
                    .background(color = Gray400, shape = RoundedCornerShape(999.dp))
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = createdTasks.toString(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Gray200,
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Concluídas",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Purple,
                    fontWeight = FontWeight.Bold
                )
            )

            Box(
                modifier = Modifier
                    .background(color = Gray400, shape = RoundedCornerShape(999.dp))
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = completedTasks.toString(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Gray200,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
fun EmptyList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Divider(color = Gray400)

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 48.dp, horizontal = 20.dp),
        ) {
            Icon(
                modifier = Modifier.size(56.dp),
                painter = painterResource(R.drawable.clipboard),
                tint = Gray400,
                contentDescription = null
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = ParagraphStyle(
                            lineHeight = 19.6.sp,
                            textAlign = TextAlign.Center
                        )
                    ) {
                        withStyle(
                            style = SpanStyle(
                                color = Gray300,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Você ainda não tem tarefas cadastradas\n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Gray300
                            )
                        ) {
                            append("Crie tarefas e organize seus itens a fazer\n")
                        }
                    }
                },
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
fun App() {
    TodoListComposeTheme(dynamicColor = false, darkTheme = false) {
        var taskValue by remember { mutableStateOf("") }

        fun handleTaskValue(value: String) {
            taskValue = value
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray600),
        ) {
            Header(value = taskValue, onValueChange = { handleTaskValue(it) })

            Spacer(modifier = Modifier.height(56.dp))

            InfoTasks()

            Spacer(modifier = Modifier.height(20.dp))

            EmptyList()
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