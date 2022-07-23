package com.example.tmdbapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.tmdbapp.PopularShowQuery
import com.example.tmdbapp.repository.TMDBRepository
import com.example.tmdbapp.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor (private val tmdbRepository: TMDBRepository) : ViewModel() {

    private val _tvShowsState = MutableLiveData<ViewState<PopularShowQuery.Data>>()
    val tvShowState get() = _tvShowsState

    fun getPopularShows() = viewModelScope.launch{
//        _tvShowsState.value = ViewState.Loading()
        _tvShowsState.postValue(ViewState.Loading())
        try {
            val response = tmdbRepository.getPopularShows()
            _tvShowsState.value = ViewState.Success(response.data!!)
        } catch (e: ApolloException){
            Log.d("@@@", "Error ${e.localizedMessage}")
            _tvShowsState.postValue(ViewState.Error(e.localizedMessage ?: "No Connection"))
        }
    }
}