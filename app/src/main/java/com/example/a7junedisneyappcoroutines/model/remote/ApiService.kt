package com.example.a7junedisneyappcoroutines.model.remote

import com.example.a7junedisneyappcoroutines.model.dataclasses.Movies
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("characters/")
    suspend fun getAllMovies(): Response<Movies>
}