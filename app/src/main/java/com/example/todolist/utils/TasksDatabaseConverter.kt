package com.example.todolist.utils

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class TasksDatabaseConverter {
    @SuppressLint("SimpleDateFormat")
    @TypeConverter
    fun convertDateToString(date: Date): Long {
        val formatter = SimpleDateFormat("Время: HH:mm, Дата: dd-MM-yyyy ")
        return date.time
    }

    @SuppressLint("SimpleDateFormat")
    @TypeConverter
    fun convertStringToDate(stringDate: Long): Date {
        val formatter = SimpleDateFormat("Время: HH:mm, Дата: dd-MM-yyyy ")
        return Date(stringDate)
    }
}