package com.example.todolist.data

import java.util.*

data class Task (
    var date: Date,
    var title: String,
    var description: String,
    var isSuccess: Boolean
)