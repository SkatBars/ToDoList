package com.example.todolist.listTask

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.EventObserver
import com.example.todolist.R
import com.example.todolist.data.Task
import com.example.todolist.databinding.ListTaskFragmentBinding
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.android.viewmodel.ext.android.viewModel

class ListTaskFragment : Fragment() {


    private val viewModel: ListTaskViewModel by viewModel()
    private lateinit var binding: ListTaskFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureBinding(inflater, container)
        configureAppBar()
        setupObserverViewModel()
        viewModel.getTasksByDate()
        return binding.root
    }

    private fun configureAppBar() {
        requireActivity().window.statusBarColor = requireActivity().getColor(R.color.blue_700)
        binding.materialToolbar.menu.getItem(0).setOnMenuItemClickListener {
            showDatePickerDialog()
            true
        }
    }

    private fun showDatePickerDialog() {
        Toast.makeText(context, "dsfsdf", Toast.LENGTH_LONG).show()
        val dpd = MaterialDatePicker.Builder.datePicker().build()
        dpd.addOnPositiveButtonClickListener {
            viewModel.updateChosenDate(it)
        }
        dpd.show(requireActivity().supportFragmentManager, "tag")
    }

    private fun setupObserverViewModel() {
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            configureRecyclerView(it)
        })

        viewModel.chosenDate.observe(viewLifecycleOwner, Observer {
            binding.date = it
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