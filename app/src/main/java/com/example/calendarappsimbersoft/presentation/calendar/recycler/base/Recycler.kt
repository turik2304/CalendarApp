package com.example.calendarappsimbersoft.presentation.calendar.recycler.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarappsimbersoft.presentation.calendar.recycler.RecyclerBuilderImpl
import io.reactivex.Observable

interface Recycler<T : ViewTyped> {

    companion object {

        @JvmOverloads
        operator fun <T : ViewTyped> invoke(
            recyclerView: RecyclerView,
            holderFactory: HolderFactory,
            diffCallback: DiffUtil.ItemCallback<T>? = null,
            init: RecyclerBuilder<T>.() -> Unit = {}
        ): Recycler<T> {
            return RecyclerBuilderImpl(
                holderFactory = holderFactory,
                diffCallback = diffCallback
            )
                .apply(init)
                .build(recyclerView)
        }

        @JvmOverloads
        operator fun <T : ViewTyped> invoke(
            recyclerView: RecyclerView,
            adapter: BaseAdapter<T>,
            init: RecyclerBuilder<T>.() -> Unit = {}
        ): Recycler<T> {
            return RecyclerBuilderImpl(adapter = adapter)
                .apply(init)
                .build(recyclerView)
        }
    }

    val recyclerView: RecyclerView

    val adapter: BaseAdapter<T>

    fun setItems(items: List<T>)
    fun <R : ViewTyped> clickedItem(vararg viewType: Int): Observable<R>
    fun <R : ViewTyped> clickedViewId(viewType: Int, viewId: Int): Observable<R>
    fun repeatOnErrorClick(): Observable<*>
}