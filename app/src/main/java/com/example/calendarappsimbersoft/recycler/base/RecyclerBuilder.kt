package com.example.calendarappsimbersoft.recycler.base

import androidx.recyclerview.widget.RecyclerView

interface RecyclerBuilder<T : ViewTyped> {
    val itemDecoration: MutableList<RecyclerView.ItemDecoration>
    val adapter: BaseAdapter<T>
    val layoutManager: RecyclerView.LayoutManager?
    var hasFixedSize: Boolean

    fun build(recyclerView: RecyclerView): Recycler<T>
}