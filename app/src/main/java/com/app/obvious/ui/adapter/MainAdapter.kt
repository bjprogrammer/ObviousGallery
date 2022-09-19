package com.app.obvious.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.app.obvious.R
import com.app.obvious.model.Image
import com.app.obvious.ui.viewholders.GridViewHolder

class MainAdapter constructor(listener: OnPressListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val imageList: MutableList<Image>
    private val listener: OnPressListener

    interface OnPressListener {
        fun onClick(position: Int, imageView: ImageView)
    }

    init {
        imageList = arrayListOf()
        this.listener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return GridViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_grid,
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gridViewHolder = holder as? GridViewHolder
        gridViewHolder?.bind(imageList[position], listener, position)
    }

    override fun getItemCount() = imageList.size

    private fun add(image: Image) {
        imageList.add(image)
        notifyItemInserted(itemCount - 1)
    }

    fun addAll(list: List<Image>) {
        for (response in list) {
            add(response)
        }
    }
}