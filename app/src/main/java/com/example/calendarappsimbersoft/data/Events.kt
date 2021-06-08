package com.example.calendarappsimbersoft.data

object Events {
    private const val HOUR_IN_MILLIS = 60L * 60L * 1000L
    const val DAY_IN_MILLIS = HOUR_IN_MILLIS * 24

    val events = listOf<Event>(
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
}