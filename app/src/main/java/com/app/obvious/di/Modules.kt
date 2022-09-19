package com.app.obvious.di

import com.app.obvious.ui.data.ImageDataSource
import com.app.obvious.ui.data.ImageRepository
import com.app.obvious.utils.AppExecutorImpl
import com.app.obvious.utils.AppExecutorsInterface
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object GsonModule {
    @Singleton
    @Provides
    fun provideGson() = GsonBuilder().create()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class SchedulerModule{
    @Binds
    abstract fun appExecutor(appExecutorImpl: AppExecutorImpl) : AppExecutorsInterface
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule{
    @Binds
    abstract fun imageDataSource(imageRepository: ImageRepository) : ImageDataSource
}