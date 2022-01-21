package com.example.todolist.listTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Event
import com.example.todolist.data.Task
import com.example.todolist.data.TaskRoomDatabase
import kotlinx.coroutines.launch
import java.util.*

class ListTaskViewModel(private val db: TaskRoomDatabase) : ViewModel() {

    private var _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() = _tasks

    private var _showToastEvent = MutableLiveData<String>()
    val showToastEvent: LiveData<String>
        get() = _showToastEvent

    private var _openCreateTaskEvent = MutableLiveData<Event<Unit>>()
    val openCreateTaskEvent: LiveData<Event<Unit>>
        get() = _openCreateTaskEvent

    private var _date = MutableLiveData<Date>(Calendar.getInstance().time)
    val chosenDate: LiveData<Date>
        get() = _date

    fun getTasksByDate() {
        val currentDate = Calendar.getInstance().time
        val tasksByCurrentDay = mutableListOf<Task>()
        viewModelScope.launch {
            val data = db.taskDao().getTasksByDate()
            data.let {
                for (task in it) {
                    val date = task.date
                    if (date.day == currentDate.day
                        && date.month == currentDate.month
                        && date.year == currentDate.year) {
                        tasksByCurrentDay.add(task)
                    }
                }
                _tasks.value = tasksByCurrentDay
            }

        }
    }

    fun changedSuccess(task: Task) {
        task.isSuccess = !(task.isSuccess)
        viewModelScope.launch {
            db.taskDao().update(task)
        }
    }

    fun openCreateTask() {
        _openCreateTaskEvent.value = Event(Unit)
    }
}