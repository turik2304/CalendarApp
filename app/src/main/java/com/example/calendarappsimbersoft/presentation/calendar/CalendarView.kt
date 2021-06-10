package com.example.calendarappsimbersoft.presentation.calendar

import com.applandeo.materialcalendarview.EventDay
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.ViewTyped

interface CalendarView {
    fun showCalendarEvents(calendarEvents: List<EventDay>)
    fun showEventsByDate(recyclerEvents: List<ViewTyped>)
}
