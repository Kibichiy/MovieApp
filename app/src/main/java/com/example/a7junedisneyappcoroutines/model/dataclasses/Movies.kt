package com.example.a7junedisneyappcoroutines.model.dataclasses

import com.example.a7junedisneyappcoroutines.model.dataclasses.Data

data class Movies(
    val count: Int,
    val `data`: List<Data>,
    val nextPage: String,
    val totalPages: Int
)