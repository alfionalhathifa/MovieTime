package com.zendev.movietime.core.data.source.remote.network;

import com.zendev.movietime.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET;
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getList(
        @Query("api_key") api_key: String
    ): ListMovieResponse
}
