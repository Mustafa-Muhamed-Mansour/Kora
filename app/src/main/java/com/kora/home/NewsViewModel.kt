package com.kora.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kora.other.Constant.Companion.API_KEY
import com.kora.other.Constant.Companion.CATEGORY
import com.kora.other.MainState
import com.kora.other.Resource
import com.kora.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {


//    private val _newsData = MutableStateFlow(emptyList<NewsResponse>())
//    val newsData = _newsData.asStateFlow()
//    private val _errorMessage = MutableStateFlow("")
//    val errorMessage = _errorMessage.asStateFlow()

    val list: MutableState<MainState> = mutableStateOf(MainState())


    fun newsByCountry(country: String) {
        viewModelScope.launch {
            list.value = MainState(isLoading = true)

            try {
                val result = newsRepository.newsByCity(country = country, category = CATEGORY, apiKey = API_KEY)

                when (result) {
                    is Resource.Error -> {
                        list.value = MainState(error = "Something went wrong")
                    }
                    is Resource.Success -> {
                        result.data?.articleModels?.let {
                            list.value = MainState(data = it)
                        }

                    }
                    is Resource.Loading -> {
                        list.value = MainState(isLoading = true)
                    }
                }
            } catch (e: Exception) {
                list.value = MainState(error = "Something went wrong")
            }
        }
    }

//    private fun newsByCity() {
//        viewModelScope.launch {
//            try {
//                val response =
//                    newsRepository.newsByCity(COUNTRY, CATEGORY, API_KEY)
//                _newsData.value = listOf(response)
//                Log.i("Fetch_Shopping", _newsData.value.toString())
//            } catch (e: Exception) {
//                _errorMessage.value = e.message.toString()
//                Log.i("Fetch_Shopping", e.message.toString())
//            }
//        }
//    }


}