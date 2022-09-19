package com.app.obvious.ui.usecases

import com.app.obvious.model.Image
import com.app.obvious.ui.data.ImageDataSource
import com.app.obvious.utils.AppExecutorsInterface
import io.reactivex.rxjava3.core.Maybe
import javax.inject.Inject

class GetImages @Inject constructor(
    private val imageDataSource: ImageDataSource,
    private val appExecutorsInterface: AppExecutorsInterface
    ): MaybeUseCase<Void, List<Image>>(appExecutorsInterface = appExecutorsInterface) {

    override fun buildUseCaseSingle(params: Void?): Maybe<List<Image>> {
        return imageDataSource.fetchData()
    }
}