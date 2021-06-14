package com.example.calendarappsimbersoft.presentation.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.example.calendarappsimbersoft.R
import com.example.calendarappsimbersoft.databinding.FragmentCreateEventBinding
import java.util.*

class CreateEventFragment : Fragment() {

    private var _binding: FragmentCreateEventBinding? = null
    private val binding get() = _binding!!
    private val startDateCalendar = GregorianCalendar()
    private val endDateCalendar = GregorianCalendar()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { initDatesOfCalendars(it) }

        binding.btnCreateEvent.setOnClickListener {
            if (startDateCalendar.timeInMillis <= endDateCalendar.timeInMillis) {
                val eventName = binding.eventInfo.edEventName.text.toString()
                val description = binding.eventInfo.edEventDescription.text.toString()
                val eventBundle = Bundle().apply {
                    putString(CalendarFragment.KEY_EVENT_NAME, eventName)
                    putString(CalendarFragment.KEY_EVENT_DESCRIPTION, description)
                    putLong(
                        CalendarFragment.KEY_START_DATE_IN_MILLIS,
                        startDateCalendar.timeInMillis
                    )
                    putLong(
                        CalendarFragment.KEY_END_DATE_IN_MILLIS,
                        endDateCalendar.timeInMillis
                    )
                }
                parentFragmentManager.setFragmentResult(
                    CalendarFragment.CREATE_EVENT_REQUEST,
                    eventBundle
                )
                parentFragmentManager.popBackStack()
            } else Toast.makeText(
                requireActivity().applicationContext,
                getString(R.string.check_time_message),
                Toast.LENGTH_SHORT
            ).show()

        }

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val resultListener = FragmentResultListener { requestKey, result ->
            val hour: Int
            val minute: Int
            when (requestKey) {
                START_TIME_PICKER_REQUEST -> {
                    hour = result.getInt(TimePickerDialogFragment.KEY_START_HOUR)
                    minute = result.getInt(TimePickerDialogFragment.KEY_START_MINUTE)
                    startDateCalendar.set(
                        Calendar.HOUR_OF_DAY,
                        hour
                    )
                    startDateCalendar.set(
                        Calendar.MINUTE,
                        minute
                    )
                    binding.eventInfo.tvStartTimeValue.text =
                        getString(R.string.time_info, hour, minute)
                }
                END_TIME_PICKER_REQUEST -> {
                    hour = result.getInt(TimePickerDialogFragment.KEY_END_HOUR)
                    minute = result.getInt(TimePickerDialogFragment.KEY_END_MINUTE)
                    endDateCalendar.set(
                        Calendar.HOUR_OF_DAY,
                        hour
                    )
                    endDateCalendar.set(
                        Calendar.MINUTE,
                        minute
                    )
                    binding.eventInfo.tvEndTimeValue.text =
                        getString(R.string.time_info, hour, minute)
                }
            }
        }

        parentFragmentManager.setFragmentResultListener(
            START_TIME_PICKER_REQUEST,
            viewLifecycleOwner,
            resultListener
        )

        parentFragmentManager.setFragmentResultListener(
            END_TIME_PICKER_REQUEST,
            viewLifecycleOwner,
            resultListener
        )

        binding.eventInfo.tvStartTimeValue.setOnClickListener {
            TimePickerDialogFragment.showStartDatePicker(parentFragmentManager)
        }

        binding.eventInfo.tvEndTimeValue.setOnClickListener {
            TimePickerDialogFragment.showEndDatePicker(parentFragmentManager)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDatesOfCalendars(dateBundle: Bundle) {
        val createDateInMillis = dateBundle.getLong(ARG_CREATE_EVENT_DATE)
        startDateCalendar.timeInMillis = createDateInMillis
        endDateCalendar.timeInMillis = createDateInMillis
        startDateCalendar.set(Calendar.HOUR_OF_DAY, 12)
        startDateCalendar.set(Calendar.MINUTE, 0)
        endDateCalendar.set(Calendar.HOUR_OF_DAY, 12)
        endDateCalendar.set(Calendar.MINUTE, 0)
    }

    companion object {
        private const val ARG_CREATE_EVENT_DATE = "ARG_CREATE_EVENT_DATE"
        const val TAG = "CREATE_EVENT_CALENDAR_FRAGMENT"
        const val START_TIME_PICKER_REQUEST = "START_TIME_PICKER_REQUEST"
        const val END_TIME_PICKER_REQUEST = "END_TIME_PICKER_REQUEST"

        fun newInstance(createEventDateInMillis: Long): CreateEventFragment {
            val fragment = CreateEventFragment()
            val arguments = Bundle().apply {
                putLong(ARG_CREATE_EVENT_DATE, createEventDateInMillis)
            }
            fragment.arguments = arguments
            return fragment
        }
    }

}