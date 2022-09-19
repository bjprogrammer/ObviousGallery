package com.app.obvious.ui.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import com.app.obvious.R
import androidx.fragment.app.Fragment
import com.app.obvious.databinding.FragmentDetailBinding
import com.app.obvious.model.ImageList
import com.app.obvious.ui.adapter.ImageAdapter
import com.app.obvious.utils.Constants.IMAGE_LIST
import com.app.obvious.utils.Constants.POSITION
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val onBackPressedCallback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Shared element animation
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

        //Changing status bar color
        val window = activity?.window
        window?.statusBarColor = Color.BLACK
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Setup viewpager
        arguments?.let {
            val currentItem = it.getInt(POSITION)
            val imageList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelableArrayList(IMAGE_LIST, ImageList.Image::class.java)
            } else {
                it.getParcelableArrayList(IMAGE_LIST)
            }?: arrayListOf<ImageList.Image>()

            binding.viewPager.apply {
                this.adapter = ImageAdapter(imageList)
                this.currentItem = currentItem
            }

            binding.backBtn.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    companion object {
        fun newInstance(current: Int, images: List<ImageList.Image>?): DetailFragment {
            val detailFragment = DetailFragment()

            // Passing image List and position of image clicked to detailed fragment
            Bundle().apply {
                putInt(POSITION, current)
                putParcelableArray(IMAGE_LIST, images?.toTypedArray())
                detailFragment.arguments = this
            }
            return detailFragment
        }
    }
}