package com.example.todolist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.todolist.utils.TasksDatabaseConverter
import java.util.*

@Entity (tableName = "tasks")
@TypeConverters(TasksDatabaseConverter::class)
data class Task (

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "isSuccess")
    var isSuccess: Boolean,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)