package com.example.calendarappsimbersoft

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.EventDay
import com.example.calendarappsimbersoft.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calendarView.setOnDayClickListener { event ->
            val timeInMillis = event.calendar.timeInMillis
            val formatter = SimpleDateFormat("dd MMMM hh mm ss")
            Log.d("xxx", "${formatter.format(timeInMillis)}")
            //recycler.adapter.setItems(getPresenter().getEvents(timeInMillis))
        }

        val events = mutableListOf<EventDay>()
        val calendar1 = Calendar.getInstance()
        val cal1  = GregorianCalendar(2021, GregorianCalendar.JUNE, 2, 17, 0)
        val cal2  = GregorianCalendar(2021, GregorianCalendar.JUNE, 2, 18, 0)
//        events.add(EventDay(cal1, R.drawable.ic_circle))
        events.add(MyEvent(cal1, R.drawable.ic_circle))
//        events.add(EventDay(cal2, R.drawable.ic_launcher_foreground))
        Log.d("xxx", "${events[0].calendar.timeInMillis}")
//        Log.d("xxx", "${events[1].calendar.timeInMillis}")
        binding.calendarView.setEvents(events)

    }

    class MyEvent(calendar: Calendar, drawable: Int) : EventDay(calendar, drawable) {


    }
}