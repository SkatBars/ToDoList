package com.example.todolist.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.example.todolist.R
import com.example.todolist.data.Task
import com.google.android.material.appbar.MaterialToolbar
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@BindingConversion()
fun convertDateToString(date: Date): String {
    val formatter = SimpleDateFormat("Время: HH:mm, Дата: dd-MM-yyyy ")
    return formatter.format(date)
}

@BindingConversion()
fun convertBooleanToString(isSuccess: Boolean): String {
    return if (isSuccess) "Задача выполнена" else "Задача ожидает выполнения"
}

@BindingAdapter("setStatusColor")
fun setStatusColor(view: View, task: Task) {
    view.setBackgroundColor(

        if (task.isSuccess) {
            view.context.getColor(R.color.green_500)
        } else {
            val currentDate = Calendar.getInstance().time
            if (currentDate.time > task.date.time) {
                view.context.getColor(R.color.red_500)
            } else {
                view.context.getColor(R.color.orange_500)
            }
        }
    )
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("setDateInTitle")
fun setDateInTitle(view: MaterialToolbar, date: Date) {
    val formatter = SimpleDateFormat("Дата: dd-MM-yyyy")
    view.title = formatter.format(date)
}