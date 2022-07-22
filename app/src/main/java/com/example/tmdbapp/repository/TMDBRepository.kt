package com.example.tmdbapp.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.example.tmdbapp.PopularShowQuery

interface TMDBRepository {
    suspend fun getPopularShows(): ApolloResponse<PopularShowQuery.Data>
}