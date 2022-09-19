package com.app.obvious.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import javax.inject.Inject

interface AppExecutorsInterface {
    fun io(): Scheduler
    fun ui(): Scheduler
}

class AppExecutorImpl @Inject constructor(): AppExecutorsInterface{
    override fun io() = Schedulers.io()
    override fun ui() = AndroidSchedulers.mainThread()
}

class TestSchedulerProvider(private val scheduler: TestScheduler) : AppExecutorsInterface {
    override fun io() = scheduler
    override fun ui() = scheduler
}