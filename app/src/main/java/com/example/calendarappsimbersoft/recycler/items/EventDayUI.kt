package com.example.calendarappsimbersoft.recycler.items

import com.example.calendarappsimbersoft.recycler.base.ViewTyped

data class EventDayUI(
    override val uid: Int,
    val timeRange: String,
    val name: String,
    val description: String,
    override val viewType: Int
) : ViewTyped