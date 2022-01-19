package com.example.todolist.createTask

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolist.R
import org.koin.android.viewmodel.ext.android.viewModel

class CreateTaskFragment : Fragment() {

    private val viewModel: CreateTaskViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_task_fragment, container, false)
    }
}