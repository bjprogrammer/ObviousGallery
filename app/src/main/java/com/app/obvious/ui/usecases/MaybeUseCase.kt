package com.app.obvious.ui.usecases

import androidx.annotation.VisibleForTesting
import com.app.obvious.utils.AppExecutorsInterface
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableMaybeObserver

abstract class MaybeUseCase<in Params, T> constructor(
    private var appExecutorsInterface: AppExecutorsInterface
) {

    private val compositeDisposable = CompositeDisposable()

    internal abstract fun buildUseCaseSingle(params: Params?): Maybe<T>

    open fun execute(observer: DisposableMaybeObserver<T>, params: Params? = null) {
        this
            .buildUseCaseSingle(null)
            .subscribeOn(appExecutorsInterface.io())
            .observeOn(appExecutorsInterface.ui())
            .subscribeWith(observer)
            .also {
                addDisposable(it)
            }
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

    fun clear() {
        if (compositeDisposable.size() > 0) compositeDisposable.clear()
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    @VisibleForTesting
    fun inspectCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }

    @VisibleForTesting
    fun setCustomExecutor(appExecutorsInterface: AppExecutorsInterface) {
        this.appExecutorsInterface = appExecutorsInterface
    }
}
