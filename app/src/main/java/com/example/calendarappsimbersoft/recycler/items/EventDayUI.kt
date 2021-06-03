package com.example.calendarappsimbersoft.recycler.items

import com.example.calendarappsimbersoft.R
import com.example.calendarappsimbersoft.recycler.base.ViewTyped

data class EventDayUI(
    val name: String,
    val timeRange: String,
    val description: String,
    override val uid: Int,
    override val viewType: Int = R.layout.item_event_day
) : ViewTyped