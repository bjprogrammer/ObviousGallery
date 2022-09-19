package com.app.obvious.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.app.obvious.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

//Function to load image from url using Glide and data binding
@BindingAdapter("url", "spinner")
fun loadImage(view: ImageView, url: String?, progressBar: ProgressBar) {
    progressBar.visibility = View.VISIBLE
    val requestBuilder = Glide.with(view.context)
        .asDrawable()
        .sizeMultiplier(0.1f)

    Glide.with(view.context)
        .load(url)
        .thumbnail(requestBuilder)
        .apply(RequestOptions().error(R.mipmap.no_image))
        .listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<Drawable?>,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                view.scaleType = ImageView.ScaleType.FIT_CENTER
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any,
                target: Target<Drawable?>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                return false
            }
        }).into(view)
}