package com.example.todolist.listTask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolist.R
import org.koin.android.viewmodel.ext.android.viewModel

class ListTaskFragment : Fragment() {


    private val viewModel: ListTaskViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().window.statusBarColor = requireActivity().getColor(R.color.blue_500)
        return inflater.inflate(R.layout.list_task_fragment, container, false)
    }

}