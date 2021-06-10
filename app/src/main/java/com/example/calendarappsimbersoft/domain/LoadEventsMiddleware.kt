package com.example.calendarappsimbersoft.domain

import com.applandeo.materialcalendarview.EventDay
import com.example.calendarappsimbersoft.R
import com.example.calendarappsimbersoft.data.CalendarRepository
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.EventDayUI
import com.example.calendarappsimbersoft.utils.DateUtils
import io.reactivex.Observable
import java.util.*

class LoadEventsMiddleware(
    override val repository: CalendarRepository,
    override val dateUtils: DateUtils
) : Middleware {

    companion object {
        private const val MILLISECONDS_PER_SECOND = 1000
    }

    private val calendarEventDrawable = R.drawable.ic_circle

    override fun getCalendarEvents(): Observable<List<EventDay>> {
        return repository.getEvents().map { events ->
            events.map {
                val calendar = GregorianCalendar()
                calendar.timeInMillis = it.startDate * MILLISECONDS_PER_SECOND
                EventDay(calendar, calendarEventDrawable)
            }
        }
    }

    override fun getEventsByDate(timeInMillis: Long): Observable<List<EventDayUI>> {
        return repository.getEvents()
            .map { events ->
                val filteredEvents = events.filter {
                    dateUtils.areDatesIsSame(it.startDate * MILLISECONDS_PER_SECOND, timeInMillis)
                }
                filteredEvents.map {
                    val formattedTime = dateUtils.formatToTimeRange(
                        it.startDate * MILLISECONDS_PER_SECOND,
                        it.endDate * MILLISECONDS_PER_SECOND
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