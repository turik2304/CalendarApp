package com.example.calendarappsimbersoft.presentation.base.presenter

import androidx.annotation.CallSuper
import com.example.calendarappsimbersoft.extensions.plusAssign
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxPresenter<View> protected constructor(viewClass: Class<View>) :
    BasePresenter<View>(viewClass) {

    private val disposables = CompositeDisposable()

    @CallSuper
    override fun detachView(isFinishing: Boolean) {
        if (isFinishing) {
            disposables.dispose()
        }
        super.detachView(isFinishing)
    }

    protected fun Disposable.disposeOnFinish(): Disposable {
        disposables += this
        return this
    }
}