package com.example.calendarappsimbersoft.presentation.calendar.recycler

import android.view.View
import com.example.calendarappsimbersoft.R
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.BaseViewHolder
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.HolderFactory
import com.example.calendarappsimbersoft.presentation.calendar.recycler.holders.EventDayHolder
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.EmptyEventDayUI

class HolderFactoryImpl: HolderFactory() {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when(viewType) {
            R.layout.item_event_day -> EventDayHolder(view, clicks)
            R.layout.item_empty_event_day -> BaseViewHolder<EmptyEventDayUI>(view)
            else -> null
        }
    }
}