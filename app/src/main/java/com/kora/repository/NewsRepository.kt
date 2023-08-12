package com.kora.repository

import com.kora.network.remote.NewsService
import com.kora.other.Resource
import com.kora.response.NewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService
) {


    suspend fun newsByCity(
        country: String,
        category: String,
        apiKey: String
    ): Resource<NewsResponse> {
        return try {
            val result = newsService.newsByCity(country, category, apiKey)
            Resource.Success(data = result)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

}