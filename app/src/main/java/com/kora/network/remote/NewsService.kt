package com.kora.network.remote

import com.kora.other.Constant.Companion.END_POINT
import com.kora.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET(END_POINT)
    suspend fun newsByCity(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}