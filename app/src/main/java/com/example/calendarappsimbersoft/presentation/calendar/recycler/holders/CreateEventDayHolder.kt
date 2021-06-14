package com.example.calendarappsimbersoft.presentation.calendar.recycler.holders

import android.view.View
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.BaseViewHolder
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.RecyclerItemClicksObservable
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.CreateEventDayUI

data class CreateEventDayHolder(
    val view: View,
    val clicks: RecyclerItemClicksObservable
) : BaseViewHolder<CreateEventDayUI>(view) {

    init {
        clicks.accept(this)
    }
}