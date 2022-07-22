package com.example.tmdbapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbapp.adapter.TVShowsAdapter
import com.example.tmdbapp.databinding.ActivityMainBinding
import com.example.tmdbapp.utils.ViewState
import com.example.tmdbapp.viewmodel.TVShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TVShowsAdapter() }
    private val viewModel by viewModels<TVShowsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getPopularShows()

        initViews()
        setupObserves()
    }

    private fun initViews() {
        with(binding){
            rvHome.layoutManager = GridLayoutManager(this@MainActivity, 2)
            rvHome.adapter = adapter
//            getTVShows()

            bFab.setOnClickListener {
                rvHome.smoothScrollToPosition(0)
            }
        }

    }

    private fun setupObserves(){
        viewModel.tvShowState.observe(this){
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
                    Log.d("@@@", "Error: ${it.message!!}")
                }
            }
        }
    }

//    private fun getTVShows(){
//        lifecycleScope.launch launchWhenResumed@{
//            val response = try {
//                GraphQL.get().query(PopularShowQuery()).execute()
//            } catch (e: ApolloException){
//                Log.d("@@@", e.toString())
//                return@launchWhenResumed
//            }
//
//            adapter.submitList(response.data!!.popularShows.shows!!)
//            Log.d("@@@", response.data!!.popularShows.shows!!.size.toString())
//        }
//    }
}