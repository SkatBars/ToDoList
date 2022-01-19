package com.example.todolist.createTask

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment.STYLE_NO_INPUT
import com.example.todolist.EventObserver
import com.example.todolist.R
import com.example.todolist.data.Task
import com.example.todolist.databinding.CreateTaskFragmentBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.Format
import java.util.*

class CreateTaskFragment : Fragment() {

    private val viewModel: CreateTaskViewModel by viewModel()
    private lateinit var binding: CreateTaskFragmentBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureBinding(inflater, container)
        observeViewModel()

        binding.dateEditBtn.setOnClickListener {
            showDatePicker()
        }

        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.create_task_fragment,
            container, false
        )
        binding.viewModel = viewModel
        binding.task = Task(Calendar.getInstance().time, "", "", false)

        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun observeViewModel() {
        viewModel.showDateTimePickerDialogEvent.observe(viewLifecycleOwner, EventObserver {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                showDatePicker()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun showDatePicker() {
        Locale.setDefault(Locale("ru", "Ru"));


        val datePickerDialog = MaterialDatePicker.Builder.datePicker().build()
        datePickerDialog.addOnPositiveButtonClickListener {
            binding.task!!.date.time = it
        }

        val timePickerDialog = MaterialTimePicker.Builder().build()
        timePickerDialog.addOnPositiveButtonClickListener {
            binding.task!!.date.minutes = timePickerDialog.minute
            binding.task!!.date.hours = timePickerDialog.hour

            binding.invalidateAll()
        }
        timePickerDialog.show(requireActivity().supportFragmentManager, "tag")
        datePickerDialog.show(requireActivity().supportFragmentManager, "tag")
    }


}