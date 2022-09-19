package com.app.obvious.ui.data

import javax.inject.Inject

class ImageRepository @Inject constructor(private val imageDataSource: ImageDataSource) {
    fun getAllImages() = imageDataSource.fetchData()
}