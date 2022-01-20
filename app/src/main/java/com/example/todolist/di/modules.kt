package com.example.todolist.di

import com.example.todolist.createTask.CreateTaskViewModel
import com.example.todolist.data.TaskDao
import com.example.todolist.data.TaskRoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    single { TaskRoomDatabase.getDatabase(androidContext()) }
    viewModel { CreateTaskViewModel(db = get()) }
}