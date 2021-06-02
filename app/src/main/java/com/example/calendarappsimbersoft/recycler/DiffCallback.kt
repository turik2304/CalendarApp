package com.example.calendarappsimbersoft.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.calendarappsimbersoft.recycler.base.ViewTyped

class DiffCallback<T : ViewTyped> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return (oldItem.uid == newItem.uid)
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}