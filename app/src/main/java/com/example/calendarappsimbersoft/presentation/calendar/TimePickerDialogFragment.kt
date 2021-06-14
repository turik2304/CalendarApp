package com.example.calendarappsimbersoft.presentation.calendar

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import java.util.*

class TimePickerDialogFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    companion object {
        const val TAG_START_TIME_PICKER = "START_TIME_PICKER"
        const val TAG_END_TIME_PICKER = "END_TIME_PICKER"
        const val KEY_START_HOUR = "ARG_START_HOUR"
        const val KEY_START_MINUTE = "ARG_START_MINUTE"
        const val KEY_END_HOUR = "ARG_END_HOUR"
        const val KEY_END_MINUTE = "ARG_END_MINUTE"

        fun showStartDatePicker(fragmentManager: FragmentManager) {
            val timePicker = TimePickerDialogFragment()
            timePicker.show(fragmentManager, TAG_START_TIME_PICKER)
        }

        fun showEndDatePicker(fragmentManager: FragmentManager) {
            val timePicker = TimePickerDialogFragment()
            timePicker.show(fragmentManager, TAG_END_TIME_PICKER)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(requireActivity(), this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        when (tag) {
            TAG_START_TIME_PICKER -> setStartTime(hourOfDay, minute)
            TAG_END_TIME_PICKER -> setEndTime(hourOfDay, minute)
        }
        dismiss()
    }

    private fun setStartTime(hour: Int, minute: Int) {
        val startTimeBundle = Bundle().apply {
            putInt(KEY_START_HOUR, hour)
            putInt(KEY_START_MINUTE, minute)
        }
        parentFragmentManager.setFragmentResult(
            CreateEventFragment.START_TIME_PICKER_REQUEST,
            startTimeBundle
        )
    }

    private fun setEndTime(hour: Int, minute: Int) {
        val endTimeBundle = Bundle().apply {
            putInt(KEY_END_HOUR, hour)
            putInt(KEY_END_MINUTE, minute)
        }
        parentFragmentManager.setFragmentResult(
            CreateEventFragment.END_TIME_PICKER_REQUEST,
            endTimeBundle
        )
    }
}