package com.example.tmdbapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbapp.R
import com.example.tmdbapp.adapter.TVShowsAdapter
import com.example.tmdbapp.databinding.ActivityMainBinding
import com.example.tmdbapp.utils.ViewState
import com.example.tmdbapp.viewmodel.TVShowsViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TVShowsAdapter() }
    private val viewModel by viewModels<TVShowsViewModel>()
    private lateinit var myToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getPopularShows()

        initViews()
    }

    private fun initViews() {
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(setOf(
            R.id.tvShowsFragment, R.id.historyFragment), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

         myToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.str_open, R.string.str_close)
        drawerLayout.addDrawerListener(myToggle)
        myToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        navView.setNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.tvShowsFragment -> {
//                    navController.navigate(R.id.tvShowsFragment)
//                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                        drawerLayout.closeDrawer(GravityCompat.START)
//                    }
//                    true
//                }
//                R.id.historyFragment -> {
//                    navController.navigate(R.id.historyFragment)
//                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                        drawerLayout.closeDrawer(GravityCompat.START)
//                    }
//                    true
//                }
//                else -> {
//                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                        drawerLayout.closeDrawer(GravityCompat.START)
//                    }
//                    false
//                }
//            }
//        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (myToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

//    private fun setupObserves(){
//        viewModel.tvShowState.observe(this){
//            when(it){
//                is ViewState.Loading -> {
//                    binding.pbLoading.visibility = View.VISIBLE
//                }
//                is ViewState.Success -> {
//                    binding.pbLoading.visibility = View.GONE
//                    adapter.submitList(it.value?.popularShows?.shows)
//                }
//                is ViewState.Error -> {
//                    binding.pbLoading.visibility = View.GONE
//                    Log.d("@@@", "Error: ${it.message!!}")
//                }
//            }
//        }
//    }

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