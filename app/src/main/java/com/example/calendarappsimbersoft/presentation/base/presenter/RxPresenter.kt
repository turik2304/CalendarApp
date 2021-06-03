package com.example.calendarappsimbersoft.presentation.base.presenter

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class RxPresenter<View> protected constructor(viewClass: Class<View>) :
    BasePresenter<View>(viewClass) {

    protected val disposables = CompositeDisposable()

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

    private operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        add(disposable)
    }
}