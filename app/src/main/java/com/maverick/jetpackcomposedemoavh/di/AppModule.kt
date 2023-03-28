package com.maverick.jetpackcomposedemoavh.di

import com.maverick.jetpackcomposedemoavh.data_layer.MovieDataSource
import com.maverick.jetpackcomposedemoavh.data_layer.MovieRepository
import com.maverick.jetpackcomposedemoavh.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideDataSource(apiService: ApiService): MovieDataSource = MovieDataSource(apiService)

    @Provides
    fun provideMovieRepository(movieDataSource: MovieDataSource): MovieRepository =
        MovieRepository(movieDataSource)

}