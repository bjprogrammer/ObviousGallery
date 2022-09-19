package com.app.obvious.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.app.obvious.model.Image
import com.app.obvious.ui.usecases.GetImages
import com.app.obvious.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(private val getImages: GetImages):ViewModel() {
    private val liveData = MutableLiveData<Response<List<Image>>>()
    fun getImages() = Transformations.map(liveData) { it }

    fun fetchImageList(){
        getImages.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                liveData.value = Response.Success(it)
            }, {
                liveData.value = Response.Error(it.message?:"")
            })
    }
}