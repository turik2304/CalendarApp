package com.example.calendarappsimbersoft.recycler.holders

import android.view.View
import android.widget.TextView
import com.example.calendarappsimbersoft.R
import com.example.calendarappsimbersoft.recycler.base.BaseViewHolder
import com.example.calendarappsimbersoft.recycler.base.RecyclerItemClicksObservable
import com.example.calendarappsimbersoft.recycler.items.EventDayUI

data class EventDayHolder(
    val view: View,
    val clicks: RecyclerItemClicksObservable
) : BaseViewHolder<EventDayUI>(view) {

    private val tvEventName = view.findViewById<TextView>(R.id.tvEventName)
    private val tvTimeRange = view.findViewById<TextView>(R.id.tvTimeRange)

    init {
        clicks.accept(this)
    }

    override fun bind(item: EventDayUI) {
        tvEventName.text = item.name
        tvTimeRange.text = item.timeRange
    }
}