package com.app.obvious.ui.data

import android.content.Context
import com.app.obvious.model.Image
import com.app.obvious.utils.fetchData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Maybe
import java.lang.reflect.Type
import javax.inject.Inject


class ImageDataSource @Inject constructor(private val gson: Gson,@ApplicationContext private val context: Context) : LocalDataSource{
    override fun fetchData(): Maybe<List<Image>> {
        return context.fetchData()?.let {
            val type: Type = object : TypeToken<List<Image>>() {}.type
            Maybe.just(gson.fromJson(it, type))
        }?: Maybe.empty()
    }
}