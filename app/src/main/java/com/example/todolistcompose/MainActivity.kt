package com.example.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistcompose.ui.theme.BlueDark
import com.example.todolistcompose.ui.theme.Gray100
import com.example.todolistcompose.ui.theme.Gray300
import com.example.todolistcompose.ui.theme.Gray500
import com.example.todolistcompose.ui.theme.Gray600
import com.example.todolistcompose.ui.theme.Gray700
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
fun Header() {
    Box(
        modifier = Modifier
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
            TextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Adicione uma nova tarefa",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Gray300
                    )
                },
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Gray700,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .heightIn(54.dp)
                    .weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Gray500,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(6.dp),
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
fun App() {
    TodoListComposeTheme(dynamicColor = false, darkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray600),
        ) {
            Header()
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