package com.example.calendarappsimbersoft.presentation.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.applandeo.materialcalendarview.EventDay
import com.example.calendarappsimbersoft.databinding.FragmentCalendarBinding
import com.example.calendarappsimbersoft.di.GlobalDI
import com.example.calendarappsimbersoft.presentation.base.MvpFragment
import com.example.calendarappsimbersoft.presentation.calendar.recycler.DiffCallback
import com.example.calendarappsimbersoft.presentation.calendar.recycler.HolderFactoryImpl
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.Recycler
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.ViewTyped
import java.util.*

class CalendarFragment : MvpFragment<CalendarView, CalendarPresenter>(), CalendarView {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private lateinit var recycler: Recycler<ViewTyped>

    override fun getPresenter(): CalendarPresenter = GlobalDI.INSTANCE.presenter

    override fun getMvpView(): CalendarView = this

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        getPresenter().loadCalendarEvents()
        getPresenter().loadEventListByDate(Calendar.getInstance().timeInMillis)
        binding.calendarView.setOnDayClickListener { event ->
            getPresenter().loadEventListByDate(event.calendar.timeInMillis)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showCalendarEvents(calendarEvents: List<EventDay>) {
        binding.calendarView.setEvents(calendarEvents)
    }

    override fun showEventsByDate(recyclerEvents: List<ViewTyped>) {
        recycler.setItems(recyclerEvents)
    }

    private fun initRecycler() {
        val divider = DividerItemDecoration(
            requireContext(),
            (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        recycler = Recycler(
            recyclerView = binding.recyclerView,
            diffCallback = DiffCallback(),
            holderFactory = HolderFactoryImpl()
        ) {
            itemDecoration += divider
        }
    }

    companion object {
        const val TAG = "CALENDAR_FRAGMENT"
        fun newInstance() = CalendarFragment()
    }
}