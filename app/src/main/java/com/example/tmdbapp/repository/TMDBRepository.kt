package com.example.tmdbapp.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.tmdbapp.PopularShowQuery
import com.example.tmdbapp.network.GraphQL
import javax.inject.Inject

class TMDBRepository @Inject constructor(private val apolloClient: ApolloClient){
     suspend fun getPopularShows(): ApolloResponse<PopularShowQuery.Data> {
        return apolloClient.query(PopularShowQuery()).execute()
    }
}