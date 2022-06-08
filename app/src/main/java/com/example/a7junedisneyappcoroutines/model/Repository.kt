package com.example.a7junedisneyappcoroutines.model

import com.example.a7junedisneyappcoroutines.model.remote.APIClient
import com.example.a7junedisneyappcoroutines.model.remote.ApiService

class Repository {

    private val retrofitService:ApiService = APIClient.getApiService()

    suspend fun getAllMovies() = retrofitService.getAllMovies()
}