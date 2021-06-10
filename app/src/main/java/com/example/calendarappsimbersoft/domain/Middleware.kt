package com.example.calendarappsimbersoft.domain

import com.applandeo.materialcalendarview.EventDay
import com.example.calendarappsimbersoft.data.CalendarRepository
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.ViewTyped
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.EventDayUI
import com.example.calendarappsimbersoft.utils.DateUtils
import io.reactivex.Observable
import io.reactivex.Single

interface Middleware {
    val repository: CalendarRepository
    val dateUtils: DateUtils
    fun getCalendarEvents(): Observable<List<EventDay>>
    fun getEventsByDate(timeInMillis: Long): Observable<List<EventDayUI>>
}