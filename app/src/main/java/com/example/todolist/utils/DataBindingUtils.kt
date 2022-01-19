package com.example.todolist.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@BindingConversion()
fun convertDateToString(date: Date): String {
    val formatter = SimpleDateFormat("Время: HH:mm, Дата: dd-MM-yyyy ")
    return formatter.format(date)
}
