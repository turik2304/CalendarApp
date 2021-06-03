package com.example.calendarappsimbersoft

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.applandeo.materialcalendarview.EventDay
import com.example.calendarappsimbersoft.databinding.ActivityMainBinding
import com.example.calendarappsimbersoft.recycler.DiffCallback
import com.example.calendarappsimbersoft.recycler.HolderFactoryImpl
import com.example.calendarappsimbersoft.recycler.base.Recycler
import com.example.calendarappsimbersoft.recycler.base.ViewTyped
import com.example.calendarappsimbersoft.recycler.items.EventDayUI
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recycler: Recycler<ViewTyped>

    private val presenter = Presenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()

        binding.calendarView.setEvents(presenter.getEvents())
        recycler.setItems(
            presenter.getEventsByDate(Calendar.getInstance().timeInMillis)
        )

        binding.calendarView.setOnDayClickListener { event ->
            val items = presenter.getEventsByDate(event.calendar.timeInMillis)
            recycler.setItems(items)
        }
    }

    private fun initRecycler() {
        val divider = DividerItemDecoration(
            applicationContext,
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

    class MyEvent(calendar: Calendar, drawable: Int) : EventDay(calendar, drawable) {
        var stub = ""
    }

}