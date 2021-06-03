package com.example.calendarappsimbersoft.presentation.calendar.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.BaseAdapter
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.Recycler
import com.example.calendarappsimbersoft.presentation.calendar.recycler.base.ViewTyped
import io.reactivex.rxjava3.core.Observable

internal class RecyclerImpl<T : ViewTyped>(
    override val recyclerView: RecyclerView,
    override val adapter: BaseAdapter<T>
) : Recycler<T> {

    override fun setItems(items: List<T>) {
        adapter.items = items
    }

    override fun <R : ViewTyped> clickedItem(vararg viewType: Int): Observable<R> {
        return adapter.holderFactory.clickPosition(*viewType)
            .map { adapter.items[it] as R }
    }

    override fun <R : ViewTyped> clickedViewId(viewType: Int, viewId: Int): Observable<R> {
        return adapter.holderFactory.clickPosition(viewType, viewId)
            .map { adapter.items[it] as R }
    }

    override fun repeatOnErrorClick(): Observable<*> {
        return adapter.holderFactory.repeatOnErrorClicks()
    }
}