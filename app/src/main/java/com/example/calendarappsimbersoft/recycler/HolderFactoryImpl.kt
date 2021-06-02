package com.example.calendarappsimbersoft.recycler

import android.view.View
import com.example.calendarappsimbersoft.R
import com.example.calendarappsimbersoft.recycler.base.BaseViewHolder
import com.example.calendarappsimbersoft.recycler.base.HolderFactory
import com.example.calendarappsimbersoft.recycler.holders.EventDayHolder

class HolderFactoryImpl: HolderFactory() {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when(viewType) {
            R.layout.item_event_day -> EventDayHolder(view, clicks)
            else -> null
        }
    }
}