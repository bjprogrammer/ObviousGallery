package com.app.obvious.ui.viewholders

import android.app.AlertDialog
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.obvious.R
import com.app.obvious.databinding.ItemPagerBinding
import com.app.obvious.model.ImageList
import com.app.obvious.utils.HelperFunctions

class ImageViewHolder(private val binding: ItemPagerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(image: ImageList.Image) {
            binding.apply {
                //Passing data for loading data through data binding
                binding.image = image

                binding.details.setOnClickListener { view ->
                    val builder = AlertDialog.Builder(view.context)
                    val arrayAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1)
                    val title = "Title : " + image.getTitle()
                    val date = "Date : " + HelperFunctions.formatDate(image.getDate())
                    val copyright = "Copyright : " + image.getCopyright()
                    val explanation = "Explanation : " + image.getExplanation()
                    arrayAdapter.add(
                        """
                    $title
                    
                    $date
                    
                    $copyright
                    
                    $explanation
                    """.trimIndent()
                    )

                    builder.apply {
                        setTitle(context.getString(R.string.image_info))
                        setAdapter(arrayAdapter, null)
                        setCancelable(true)
                        show()
                    }
                }

                imageView.transitionName = image.getTitle()
            }
        }
    }