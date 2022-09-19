package com.app.obvious.ui.fragments

import com.app.obvious.ui.viewmodel.GridViewModel
import com.app.obvious.ui.adapter.MainAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.app.obvious.R
import com.app.obvious.ui.adapter.MainAdapter.OnPressListener
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.obvious.databinding.FragmentRecyclerViewBinding
import com.app.obvious.model.ImageList
import com.app.obvious.utils.Constants.GRID_SIZE
import com.app.obvious.utils.Response
import com.app.obvious.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class GridFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerViewBinding
    private lateinit var adapter: MainAdapter
    private val viewModel: GridViewModel by viewModels()
    private var imageList: List<ImageList.Image>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_view, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        binding.progressBar.visibility = View.VISIBLE
        viewModel.fetchImageList()
    }

    private fun observeViewModel() {
        viewModel.getImages().observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.GONE
            if(it is Response.Success){
                if (it.data.getData()?.isEmpty()?.not() == true) {
                    imageList = it.data.getData()
                    adapter.addAll(it.data.getData()!!)

                    binding.apply {
                        recyclerView.visibility = View.VISIBLE
                        tvEmpty.visibility = View.GONE
                    }
                } else {
                    showNoListView()
                }
            } else if (it is Response.Error){
                showNoListView()
                context?.showToast(it.msg)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = MainAdapter(object : OnPressListener {
            override fun onClick(position: Int, imageView: ImageView) {
                //Adding new fragment with shared element transition when a image is clicked
                DetailFragment.newInstance(position, imageList).apply {
                    parentFragmentManager
                        .beginTransaction()
                        .addSharedElement(imageView, ViewCompat.getTransitionName(imageView)!!)
                        .addToBackStack(TAG)
                        .replace(R.id.content, this@GridFragment)
                        .commit()
                }
            }
        })

        binding.recyclerView.apply {
            val gridLayoutManager = GridLayoutManager(context, GRID_SIZE)
            layoutManager = gridLayoutManager
            itemAnimator = DefaultItemAnimator()
            adapter = adapter
        }
    }

    private fun showNoListView() {
        binding.apply {
            recyclerView.visibility = View.GONE
            tvEmpty.visibility = View.VISIBLE
        }
    }

    companion object {
        val TAG = GridFragment::class.java.simpleName
        fun newInstance(): GridFragment {
            return GridFragment()
        }
    }
}