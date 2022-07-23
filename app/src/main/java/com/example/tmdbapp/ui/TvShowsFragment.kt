package com.example.tmdbapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbapp.R
import com.example.tmdbapp.adapter.TVShowsAdapter
import com.example.tmdbapp.databinding.FragmentTvShowsBinding
import com.example.tmdbapp.utils.Logger
import com.example.tmdbapp.utils.ViewState
import com.example.tmdbapp.viewmodel.TVShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class TvShowsFragment : Fragment(R.layout.fragment_tv_shows) {
    private val binding by viewBinding { FragmentTvShowsBinding.bind(it) }
    private val adapter by lazy { TVShowsAdapter() }
    private val viewModel by viewModels<TVShowsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getPopularShows()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setupObserves()
    }

    private fun initViews() {
        with(binding){
            rvHome.layoutManager = GridLayoutManager(requireContext(), 2)
            rvHome.adapter = adapter

            bFab.setOnClickListener {
                rvHome.smoothScrollToPosition(0)
            }
        }
    }

    private fun setupObserves(){
        viewModel.tvShowState.observe(viewLifecycleOwner){
            when(it){
                is ViewState.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    adapter.submitList(it.value?.popularShows?.shows)
                }
                is ViewState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    Logger.d("@@@", "Error: ${it.message!!}")
                }
            }
        }
    }
}