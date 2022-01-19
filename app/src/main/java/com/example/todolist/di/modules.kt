package com.example.todolist.di

import com.example.todolist.createTask.CreateTaskViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    viewModel { CreateTaskViewModel() }
}