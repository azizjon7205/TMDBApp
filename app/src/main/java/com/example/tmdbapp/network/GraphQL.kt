package com.example.tmdbapp.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object GraphQL {
    val BASE_URL = "https://ql-movie-api.herokuapp.com/graphql"

    val IS_TESTER = true
    val SERVER_DEVELOPMENT = "https://ql-movie-api.herokuapp.com/graphql"
    val SERVER_PRODUCTION = "https://ql-movie-api.herokuapp.com/graphql"

}