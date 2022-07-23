package com.example.tmdbapp.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.tmdbapp.network.GraphQL
import com.example.tmdbapp.network.GraphQL.IS_TESTER
import com.example.tmdbapp.network.GraphQL.SERVER_DEVELOPMENT
import com.example.tmdbapp.network.GraphQL.SERVER_PRODUCTION
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun server(): String{
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    @Provides
    @Singleton
    fun httpClient(): OkHttpClient  {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }


    @Provides
    @Singleton
    fun apolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(server())
            .okHttpClient(httpClient())
            .build()
    }
}