package com.app.obvious.ui.viewholders

import android.view.View
import androidx.core.view.ViewCompat
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.app.obvious.R
import com.app.obvious.model.Image
import com.app.obvious.ui.adapter.MainAdapter

class GridViewHolder internal constructor(private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {
        
    fun bind(obj: Image, listener: MainAdapter.OnPressListener, position: Int) {
        binding.apply {
            ViewCompat.setTransitionName(
                root.findViewById(R.id.gridView),
                obj.getTitle()
            )

            root.setOnClickListener { v: View? ->
                listener.onClick(
                    position,
                    root.findViewById(R.id.gridView)
                )
            }

            setVariable(BR.image, obj)
            executePendingBindings()
        }
    }
}
