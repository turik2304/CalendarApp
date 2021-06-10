package com.example.calendarappsimbersoft.presentation.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
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
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.EmptyEventDayUI
import java.util.*

class CalendarFragment : MvpFragment<CalendarView, CalendarRxPresenter>(), CalendarView {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private lateinit var recycler: Recycler<ViewTyped>

    override fun getPresenter(): CalendarRxPresenter = GlobalDI.INSTANCE.presenter

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

    override fun showLoading() {
        binding.recyclerView.isGone = true
        binding.progressBar.isVisible = true
    }

    override fun showCalendarEvents(calendarEvents: List<EventDay>) {
        binding.recyclerView.isVisible = true
        binding.progressBar.isGone = true
        binding.calendarView.setEvents(calendarEvents)
    }

    override fun showEventsByDate(recyclerEvents: List<ViewTyped>) {
        binding.recyclerView.isVisible = true
        binding.progressBar.isGone = true
        recycler.setItems(recyclerEvents)
    }

    override fun showEmptyEvent() {
        binding.recyclerView.isVisible = true
        binding.progressBar.isGone = true
        recycler.setItems(listOf(EmptyEventDayUI(0)))
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