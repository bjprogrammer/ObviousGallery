package com.app.obvious.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.obvious.R
import com.app.obvious.model.ImageList
import com.app.obvious.ui.viewholders.ImageViewHolder

class ImageAdapter(private val images: List<ImageList.Image>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return ImageViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_pager,
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ImageViewHolder)?.bindView(images[position])
    }

    override fun getItemCount() = images.size
}
