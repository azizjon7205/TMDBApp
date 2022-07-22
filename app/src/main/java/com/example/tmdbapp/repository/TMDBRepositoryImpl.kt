package com.example.tmdbapp.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.example.tmdbapp.PopularShowQuery
import com.example.tmdbapp.network.GraphQL
import javax.inject.Inject

class TMDBRepositoryImpl @Inject constructor(private val tvShowsService: GraphQL): TMDBRepository {
    override suspend fun getPopularShows(): ApolloResponse<PopularShowQuery.Data> {
        return tvShowsService.get().query(PopularShowQuery()).execute()
    }
}