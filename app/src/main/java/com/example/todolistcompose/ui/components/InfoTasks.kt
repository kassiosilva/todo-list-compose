package com.example.todolistcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistcompose.ui.theme.Blue
import com.example.todolistcompose.ui.theme.Gray200
import com.example.todolistcompose.ui.theme.Gray400
import com.example.todolistcompose.ui.theme.Purple

@Composable
fun InfoTasks(modifier: Modifier = Modifier, createdTasks: Int = 0, completedTasks: Int = 0) {
  Row(
    modifier = modifier
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "Criadas",
        style = MaterialTheme.typography.bodyMedium.copy(
          color = Blue, fontWeight = FontWeight.Bold
        ),
      )

      Box(
        modifier = Modifier
          .background(color = Gray400, shape = RoundedCornerShape(999.dp))
          .padding(horizontal = 8.dp), contentAlignment = Alignment.Center
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
        text = "Concluídas", style = MaterialTheme.typography.bodyMedium.copy(
          color = Purple, fontWeight = FontWeight.Bold
        )
      )

      Box(
        modifier = Modifier
          .background(color = Gray400, shape = RoundedCornerShape(999.dp))
          .padding(horizontal = 8.dp), contentAlignment = Alignment.Center
      ) {
        Text(
          text = completedTasks.toString(),
          style = MaterialTheme.typography.bodySmall.copy(
            color = Gray200, fontWeight = FontWeight.Bold
          )
        )
      }
    }
  }
}

@Preview
@Composable
private fun InfoTasksPreview() {
  InfoTasks()
}
