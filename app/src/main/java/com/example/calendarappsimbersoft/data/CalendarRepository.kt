package com.example.calendarappsimbersoft.data

interface CalendarRepository {
    fun getEvents(): List<Event>
}