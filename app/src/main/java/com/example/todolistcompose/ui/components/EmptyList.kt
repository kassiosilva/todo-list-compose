package com.example.todolistcompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistcompose.R
import com.example.todolistcompose.ui.theme.Gray300
import com.example.todolistcompose.ui.theme.Gray400

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
              lineHeight = 19.6.sp, textAlign = TextAlign.Center
            )
          ) {
            withStyle(
              style = SpanStyle(
                color = Gray300, fontWeight = FontWeight.Bold
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

@Preview
@Composable
fun EmptyListPreview() {
  EmptyList()
}
