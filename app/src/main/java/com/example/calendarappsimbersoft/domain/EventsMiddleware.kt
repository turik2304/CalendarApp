package com.example.calendarappsimbersoft.domain

import com.applandeo.materialcalendarview.EventDay
import com.example.calendarappsimbersoft.R
import com.example.calendarappsimbersoft.data.CalendarRepository
import com.example.calendarappsimbersoft.data.Event
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.EventDayUI
import com.example.calendarappsimbersoft.utils.DateUtils
import io.reactivex.Observable
import java.util.*

class EventsMiddleware(
    override val repository: CalendarRepository,
    override val dateUtils: DateUtils
) : Middleware {

    companion object {
        private const val MILLISECONDS_PER_SECOND = 1000
    }

    private val calendarEventDrawable = R.drawable.ic_circle
    private var autoIncrementPrimaryKey = 0
        set(value) {
            if (value > field) {
                field = value
            }
        }
        get() {
            return ++field
        }

    override fun addEvent(
        name: String,
        description: String,
        startDateInMillis: Long,
        endDateInMillis: Long,
    ) {
        repository.addEvent(
            Event(
                name = name,
                description = description,
                startDateInSeconds = startDateInMillis / MILLISECONDS_PER_SECOND,
                endDateInSeconds = endDateInMillis / MILLISECONDS_PER_SECOND,
                autoIncrementPrimaryKey
            )
        )
    }

    override fun getCalendarEvents(): Observable<List<EventDay>> {
        return repository.getEvents().map { events ->
            events.map {
                autoIncrementPrimaryKey = it.id
                val calendar = GregorianCalendar()
                calendar.timeInMillis = it.startDateInSeconds * MILLISECONDS_PER_SECOND
                EventDay(calendar, calendarEventDrawable)
            }
        }
    }

    override fun getEventsByDate(timeInMillis: Long): Observable<List<EventDayUI>> {
        return repository.getEvents()
            .map { events ->
                val filteredEvents = events.filter {
                    dateUtils.areDatesIsSame(
                        it.startDateInSeconds * MILLISECONDS_PER_SECOND,
                        timeInMillis
                    )
                }
                filteredEvents.map {
                    val formattedTime = dateUtils.formatToTimeRange(
                        it.startDateInSeconds * MILLISECONDS_PER_SECOND,
                        it.endDateInSeconds * MILLISECONDS_PER_SECOND
                    )
                    EventDayUI(
                        name = it.name,
                        description = it.description,
                        timeRange = formattedTime,
                        uid = it.id
                    )
                }
            }
    }
}