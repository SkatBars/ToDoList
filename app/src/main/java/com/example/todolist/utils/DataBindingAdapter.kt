package com.example.todolist.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@BindingAdapter("setDate")
fun convertDateToString(view: TextView, date: Date) {
    val formatter = SimpleDateFormat("Время: h:mm, Дата: dd-MM-yyyy ")
    view.text = formatter.format(date)
}