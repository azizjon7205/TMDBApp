package com.example.tmdbapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tmdbapp.R
import com.example.tmdbapp.databinding.FragmentMoviesBinding
import viewBinding

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val binding by viewBinding { FragmentMoviesBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {

    }

}