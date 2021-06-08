package com.example.calendarappsimbersoft.domain

import com.applandeo.materialcalendarview.EventDay
import com.example.calendarappsimbersoft.R
import com.example.calendarappsimbersoft.data.Events
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.ViewTyped
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.EmptyEventDayUI
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.EventDayUI
import java.text.SimpleDateFormat
import java.util.*

class LoadEventsMiddleware : Middleware {

    private val events = Events.events
    private val drawable = R.drawable.ic_circle
    private val formatterForComparisons = SimpleDateFormat("dd MM yyyy")
    private val timeRangeFormatter = SimpleDateFormat("HH:mm")

    fun getEvents(): List<EventDay> {
        return events.map {
            val calendar = GregorianCalendar()
            calendar.timeInMillis = it.startDate
            EventDay(calendar, drawable)
        }
    }

    fun getEventsByDate(timeInMillis: Long): List<ViewTyped> {
        val events = events.filter {
            formatterForComparisons.format(it.startDate) == formatterForComparisons.format(
                timeInMillis
            )
        }
            .map {
                EventDayUI(
                    name = it.name,
                    description = it.description,
                    timeRange = timeRangeFormatter.format(it.startDate) + "-" + timeRangeFormatter.format(
                        it.endDate
                    ),
                    uid = it.id
                )
            }
        return if (events.isNotEmpty()) events else listOf(EmptyEventDayUI(0))
    }
}