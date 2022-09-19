package com.app.obvious.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.app.obvious.model.ImageList
import com.app.obvious.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor():ViewModel() {
    fun fetchImageList(){

    }

    private val liveData = MutableLiveData<Response<ImageList>>()

    fun getImages() = Transformations.map(liveData) {
        it
    }
}