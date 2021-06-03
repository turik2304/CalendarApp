package com.example.calendarappsimbersoft.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.calendarappsimbersoft.presentation.base.presenter.Presenter

abstract class MvpFragment<View, P : Presenter<View>> : Fragment(),
    MvpViewCallback<View, P> {

    private val mvpHelper: MvpHelper<View, P> by lazy { MvpHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpHelper.create()
    }

    override fun onDestroy() {
        val isFinishing = isRemoving || requireActivity().isFinishing
        mvpHelper.destroy(isFinishing)
        super.onDestroy()
    }
}