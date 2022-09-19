package com.app.obvious.di

import com.google.gson.GsonBuilder
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