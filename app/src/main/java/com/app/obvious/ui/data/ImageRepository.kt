package com.app.obvious.ui.data

import dagger.Binds
import javax.inject.Inject


class ImageRepository @Inject constructor(private val localDataSource:  LocalDataSource): ImageDataSource {
    override fun getAllImages() = localDataSource.fetchData()
}