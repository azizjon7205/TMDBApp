package com.example.tmdbapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.PopularShowQuery
import com.example.tmdbapp.R
import com.example.tmdbapp.adapter.TVShowsAdapter
import com.example.tmdbapp.databinding.FragmentHistoryBinding
import com.example.tmdbapp.utils.Logger
import com.example.tmdbapp.viewmodel.TVShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {
    private val TAG = this::class.java.simpleName
    private val binding by viewBinding{FragmentHistoryBinding.bind(it)}
    private val viewModel by viewModels<TVShowsViewModel>()

    private val adapterTvShowAdapter by lazy { TVShowsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        viewModel.getTvShowsFromDB()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObserves()
    }

    private fun initViews() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvHistory.layoutManager = gridLayoutManager
        binding.rvHistory.adapter = adapterTvShowAdapter

//        adapterTvShowAdapter.onClick = { tvShow, ivMovie ->
////            callDetailsActivity(tvShow, ivMovie)
//        }

        binding.bFab.setOnClickListener {
            binding.rvHistory.smoothScrollToPosition(0)
        }

    }

    private fun initObserves() {

    }

    private fun callDetailsActivity(tvShow: PopularShowQuery.Show, sharedImageView: ImageView) {
//        val intent = Intent(requireContext(), DetailsActivity::class.java)
//        intent.putExtra("show_id", tvShow.id)
//        intent.putExtra("show_img", tvShow.image_thumbnail_path)
//        intent.putExtra("show_name", tvShow.name)
//        intent.putExtra("show_network", tvShow.network)
//        intent.putExtra("iv_movie", ViewCompat.getTransitionName(sharedImageView))
//
//        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//            requireActivity(),
//            sharedImageView,
//            ViewCompat.getTransitionName(sharedImageView)!!
//        )
//
//        startActivity(intent, options.toBundle())
    }

}