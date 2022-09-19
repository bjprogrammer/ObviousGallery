package com.app.obvious.ui.usecases

import com.app.obvious.ui.data.ImageRepository
import javax.inject.Inject

class GetImages @Inject constructor(private val imageRepository: ImageRepository) {
    fun invoke() = imageRepository.getAllImages()
}