package com.example.calendarappsimbersoft.presentation.calendar.recycler.items

import com.example.calendarappsimbersoft.R
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.ViewTyped

data class EmptyEventDayUI(
    override val uid: Int,
    override val viewType: Int = R.layout.item_empty_event_day
) : ViewTyped