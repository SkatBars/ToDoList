package com.example.todolist.data

import java.util.*

data class Task (
    val date: Date,
    val title: String,
    val description: String,
    val isSuccess: Boolean
)