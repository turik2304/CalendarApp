package com.example.calendarappsimbersoft.domain

import com.applandeo.materialcalendarview.EventDay
import com.example.calendarappsimbersoft.data.CalendarRepository
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.ViewTyped
import com.example.calendarappsimbersoft.utils.DateUtils

interface Middleware {
    val repository: CalendarRepository
    val dateUtils: DateUtils
    fun getCalendarEvents(): List<EventDay>
    fun getEventsByDate(timeInMillis: Long): List<ViewTyped>
}