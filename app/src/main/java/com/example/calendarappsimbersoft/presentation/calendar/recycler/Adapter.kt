package com.example.calendarappsimbersoft.presentation.calendar.recycler

import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.BaseAdapter
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.HolderFactory
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.ViewTyped

class Adapter<T : ViewTyped>(holderFactory: HolderFactory) :
    BaseAdapter<T>(holderFactory) {

    private val localItems = mutableListOf<T>()

    override var items: List<T>
        get() = localItems
        set(newItems) {
            localItems.clear()
            localItems.addAll(newItems)
            notifyDataSetChanged()
        }
}