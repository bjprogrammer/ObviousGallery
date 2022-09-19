package com.app.obvious.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.app.obvious.R
import com.app.obvious.model.ImageList
import com.app.obvious.ui.viewholders.GridViewHolder
import java.util.*

class MainAdapter constructor(listener: OnPressListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val imageList: MutableList<ImageList.Image>
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

    private fun add(image: ImageList.Image) {
        imageList.add(image)
        notifyItemInserted(imageList.size - 1)
    }

    fun addAll(list: List<ImageList.Image>) {
        for (response in list) {
            add(response)
        }
    }
}