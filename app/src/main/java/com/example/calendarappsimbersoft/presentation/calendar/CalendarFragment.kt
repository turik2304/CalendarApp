package com.example.calendarappsimbersoft.presentation.calendar

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.applandeo.materialcalendarview.EventDay
import com.example.calendarappsimbersoft.R
import com.example.calendarappsimbersoft.databinding.FragmentCalendarBinding
import com.example.calendarappsimbersoft.di.GlobalDI
import com.example.calendarappsimbersoft.extensions.plusAssign
import com.example.calendarappsimbersoft.presentation.base.MvpFragment
import com.example.calendarappsimbersoft.presentation.calendar.recycler.DiffCallback
import com.example.calendarappsimbersoft.presentation.calendar.recycler.HolderFactoryImpl
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.Recycler
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.ViewTyped
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.CreateEventDayUI
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.EventDayUI
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class CalendarFragment : MvpFragment<CalendarView, CalendarRxPresenter>(), CalendarView {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private val disposables = CompositeDisposable()

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
        initRecyclerClicks()
        initFragmentResultListener()
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

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
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

    private fun showEventInfoDialog(event: EventDayUI) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        val view = layoutInflater.inflate(R.layout.event_info, null)
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()
        view.findViewById<EditText>(R.id.edEventName).apply {
            isEnabled = false
            setText(event.name)
        }
        view.findViewById<EditText>(R.id.edEventDescription).apply {
            isEnabled = false
            setText(event.description)
        }
        view.findViewById<TextView>(R.id.tvStartTimeInfo).text = getString(R.string.time_hint)
        view.findViewById<TextView>(R.id.tvStartTimeValue).text = event.timeRange
        view.findViewById<TextView>(R.id.tvEndTimeInfo).isVisible = false
        view.findViewById<TextView>(R.id.tvEndTimeValue).isVisible = false
        dialog.show()
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

    private fun initRecyclerClicks() {
        val createEventClick =
            recycler.clickedItem<CreateEventDayUI>(R.layout.item_create_event_day)
        val openEventClick =
            recycler.clickedItem<EventDayUI>(R.layout.item_event_day)

        disposables += openEventClick.subscribe { event ->
            showEventInfoDialog(event)
        }

        disposables += createEventClick.subscribe {
            parentFragmentManager.beginTransaction()
                .add(
                    binding.root.id,
                    CreateEventFragment.newInstance(it.createEventDateInMillis),
                    CreateEventFragment.TAG
                )
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initFragmentResultListener() {
        val resultListener = FragmentResultListener { requestKey, result ->
            if (requestKey == CREATE_EVENT_REQUEST) {
                val eventName = result.getString(KEY_EVENT_NAME)
                val description = result.getString(KEY_EVENT_DESCRIPTION)
                val startDateInMillis = result.getLong(KEY_START_DATE_IN_MILLIS)
                val endDateInMillis = result.getLong(KEY_END_DATE_IN_MILLIS)
                if (eventName != null && description != null) {
                    getPresenter().addEvent(
                        name = eventName,
                        description = description,
                        startDateInMillis = startDateInMillis,
                        endDateInMillis = endDateInMillis
                    )
                    getPresenter().loadCalendarEvents()
                    getPresenter().loadEventListByDate(startDateInMillis)
                }
            }
        }
        parentFragmentManager.setFragmentResultListener(
            CREATE_EVENT_REQUEST,
            viewLifecycleOwner,
            resultListener
        )
    }

    companion object {
        const val TAG = "CALENDAR_FRAGMENT"
        const val CREATE_EVENT_REQUEST = "CREATE_EVENT_REQUEST"
        const val KEY_EVENT_NAME = "KEY_EVENT_NAME"
        const val KEY_EVENT_DESCRIPTION = "KEY_EVENT_DESCRIPTION"
        const val KEY_START_DATE_IN_MILLIS = "KEY_START_DATE_IN_MILLIS"
        const val KEY_END_DATE_IN_MILLIS = "KEY_END_DATE_IN_MILLIS"
        fun newInstance() = CalendarFragment()
    }
}