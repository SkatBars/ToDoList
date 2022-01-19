package com.example.todolist.createTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.Event

class CreateTaskViewModel : ViewModel() {
    private var _showDateTimePickerDialogEvent = MutableLiveData<Event<Unit>>()
    val showDateTimePickerDialogEvent: LiveData<Event<Unit>>
        get() = _showDateTimePickerDialogEvent


    fun showDateTimePickerDialog() {
        _showDateTimePickerDialogEvent.value = Event(Unit)
    }
}