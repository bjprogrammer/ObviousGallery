package com.app.obvious.ui.data

import com.app.obvious.model.Image
import io.reactivex.rxjava3.core.Maybe

interface LocalDataSource {
    fun fetchData(): Maybe<List<Image>>
}