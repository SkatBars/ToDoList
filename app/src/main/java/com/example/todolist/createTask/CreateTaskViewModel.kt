package com.example.todolist.createTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Event
import com.example.todolist.data.Task
import com.example.todolist.data.TaskRoomDatabase
import kotlinx.coroutines.launch
import java.util.*

class CreateTaskViewModel(private val db: TaskRoomDatabase) : ViewModel() {
    private var _showDateTimePickerDialogEvent = MutableLiveData<Event<Unit>>()
    val showDateTimePickerDialogEvent: LiveData<Event<Unit>>
        get() = _showDateTimePickerDialogEvent

    private var _openListTaskFragmentEvent = MutableLiveData<Event<Unit>>()
    val openListTaskFragmentEvent: LiveData<Event<Unit>>
        get() = _openListTaskFragmentEvent

    private var _showToastEvent = MutableLiveData<String>()
    val showToastEvent: LiveData<String>
        get() = _showToastEvent


    fun insertTask(task: Task) {
        viewModelScope.launch {
            db.taskDao().insert(task)
            openListTaskFragment()
        }.start()
    }

    fun showToast(text: String) {
        _showToastEvent.value = text
    }

    fun showDateTimePickerDialog() {
        _showDateTimePickerDialogEvent.value = Event(Unit)
    }

    fun openListTaskFragment() {
        _openListTaskFragmentEvent.value = Event(Unit)
    }


}