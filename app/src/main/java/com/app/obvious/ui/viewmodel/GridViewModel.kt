package com.app.obvious.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.app.obvious.model.Image
import com.app.obvious.ui.usecases.GetImages
import com.app.obvious.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.observers.DisposableMaybeObserver
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(private val getImages: GetImages):ViewModel() {
    private val liveData = MutableLiveData<Response<List<Image>>>()
    fun getImages() = Transformations.map(liveData) { it }

    fun fetchImageList(){
        getImages.execute(
            observer = object: DisposableMaybeObserver<List<Image>>(){
                override fun onSuccess(t: List<Image>) {
                    liveData.value = Response.Success(t)
                }

                override fun onError(e: Throwable) {
                    liveData.value = Response.Error(e.message?:"")
                }

                override fun onComplete() {}
            }
        )
    }
}