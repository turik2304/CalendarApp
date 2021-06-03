package com.example.calendarappsimbersoft

import com.applandeo.materialcalendarview.EventDay
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.ViewTyped
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.EmptyEventDayUI
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.EventDayUI
import java.text.SimpleDateFormat
import java.util.*

class Presenter {

    companion object {
        private const val HOUR_IN_MILLIS = 60L * 60L * 1000L
        const val DAY_IN_MILLIS = HOUR_IN_MILLIS * 24
    }

    private val events = listOf<Event>(
        Event(
            name = "event1",
            description = "desc1",
            startDate = 1622581200000 + HOUR_IN_MILLIS,
            endDate = 1622581200000 + 2 * HOUR_IN_MILLIS,
            id = 0
        ),
        Event(
            name = "event2",
            description = "desc2",
            startDate = 1622581200000 + 2 * HOUR_IN_MILLIS,
            endDate = 1622581200000 + 3 * HOUR_IN_MILLIS,
            id = 1
        ),
        Event(
            name = "event3",
            description = "desc3",
            startDate = 1622581200000 + 3 * HOUR_IN_MILLIS,
            endDate = 1622581200000 + 4 * HOUR_IN_MILLIS,
            id = 2
        ),
        Event(
            name = "event4",
            description = "desc4",
            startDate = 1622581200000 + 24 * HOUR_IN_MILLIS,
            endDate = 1622581200000 + 25 * HOUR_IN_MILLIS,
            id = 3
        ),
        Event(
            name = "event5",
            description = "desc5",
            startDate = 1622581200000 + 48 * HOUR_IN_MILLIS,
            endDate = 1622581200000 + 49 * HOUR_IN_MILLIS,
            id = 4
        )
    )
    private val drawable = R.drawable.ic_circle

    fun getEvents(): List<EventDay> {
        return events.map {
            val calendar = GregorianCalendar()
            calendar.timeInMillis = it.startDate
            EventDay(calendar, drawable)
        }
    }

    fun getEventsByDate(timeInMillis: Long): List<ViewTyped> {
        val formatterForComparisons = SimpleDateFormat("dd MM yyyy")
        val timeRangeFormatter = SimpleDateFormat("HH:mm")
        val events = events.filter {
            formatterForComparisons.format(it.startDate) == formatterForComparisons.format(timeInMillis)
        }
            .map {
                EventDayUI(
                    name = it.name,
                    description = it.description,
                    timeRange = timeRangeFormatter.format(it.startDate) + "-" + timeRangeFormatter.format(it.endDate),
                    uid = it.id
                )
            }
        return if (events.isNotEmpty()) events else listOf(EmptyEventDayUI(0))
    }

}