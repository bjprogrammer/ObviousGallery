package com.app.obvious.ui.data

import com.app.obvious.model.Image
import io.reactivex.rxjava3.core.Maybe

interface ImageDataSource {
    fun getAllImages(): Maybe<List<Image>>
}