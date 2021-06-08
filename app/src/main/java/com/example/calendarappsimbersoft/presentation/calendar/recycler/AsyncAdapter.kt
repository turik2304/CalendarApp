package com.example.calendarappsimbersoft.presentation.calendar.recycler

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.BaseAdapter
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.HolderFactory
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.ViewTyped

class AsyncAdapter<T : ViewTyped>(
    holderFactory: HolderFactory,
    diffCallback: DiffUtil.ItemCallback<T>
) :
    BaseAdapter<T>(holderFactory) {

    private val localItems = AsyncListDiffer(this, diffCallback)

    override var items: List<T>
        get() = localItems.currentList
        set(newItems) {
            localItems.submitList(newItems)
        }
}