package com.example.calendarappsimbersoft.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarappsimbersoft.presentation.base.presenter.Presenter

abstract class MvpActivity<View, P : Presenter<View>> : AppCompatActivity(),
    MvpViewCallback<View, P> {

    private val mvpHelper: MvpHelper<View, P> by lazy { MvpHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpHelper.create()
    }

    override fun onDestroy() {
        mvpHelper.destroy(isFinishing)
        super.onDestroy()
    }

}