package com.kora.di

import com.kora.network.remote.NewsService
import com.kora.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {


    @Provides
    fun providesNewsByCity(newsService: NewsService): NewsRepository {
        return NewsRepository(newsService)
    }

}