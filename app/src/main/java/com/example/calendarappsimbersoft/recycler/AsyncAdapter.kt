package com.example.calendarappsimbersoft.recycler

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.calendarappsimbersoft.recycler.base.BaseAdapter
import com.example.calendarappsimbersoft.recycler.base.HolderFactory
import com.example.calendarappsimbersoft.recycler.base.ViewTyped

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

    fun setItemsWithCommitCallback(
        newItems: List<T>,
        runnable: Runnable
    ) {
        localItems.submitList(newItems) {
            runnable.run()
        }
    }
}