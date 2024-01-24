package com.example.todolistcompose.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Task(val id: String, val label: String, var initialChecked: Boolean = false) {
    var checked by mutableStateOf(initialChecked)
}
