package com.kora.di

import com.kora.home.NewsViewModel
import com.kora.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {


    @Provides
    fun providesNewsByCity(newsRepository: NewsRepository): NewsViewModel {
        return NewsViewModel(newsRepository)
    }

}