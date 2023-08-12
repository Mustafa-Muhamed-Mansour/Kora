package com.kora.other

import com.kora.model.ArticleModel


data class MainState(
    val isLoading: Boolean = false,
    val data: List<ArticleModel> = emptyList(),
    val error: String = ""
)
