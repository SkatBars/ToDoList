package com.example.todolist.listTask

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.EventObserver
import com.example.todolist.R
import com.example.todolist.data.Task
import com.example.todolist.databinding.ListTaskFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ListTaskFragment : Fragment() {


    private val viewModel: ListTaskViewModel by viewModel()
    private lateinit var binding: ListTaskFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureAppBar()
        configureBinding(inflater, container)
        setupObserverViewModel()
        viewModel.getTasksByDate()
        return binding.root
    }

    private fun configureAppBar() {
        requireActivity().window.statusBarColor = requireActivity().getColor(R.color.blue_700)
    }

    private fun setupObserverViewModel() {
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            configureRecyclerView(it)
        })

        viewModel.openCreateTaskEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_listTaskFragment_to_createTaskFragment)
        })
    }

    private fun configureBinding(inflater: LayoutInflater, container: ViewGroup?) {
            binding = DataBindingUtil.inflate(inflater, R.layout.list_task_fragment, container, false)
            binding.viewModel = viewModel
    }

    private fun configureRecyclerView(tasks: List<Task>) {
        val layoutManager = LinearLayoutManager(context)
        binding.taskRecyclerView.adapter = TaskAdapterRecyclerView(tasks, viewModel)
        binding.taskRecyclerView.layoutManager = layoutManager
    }

}