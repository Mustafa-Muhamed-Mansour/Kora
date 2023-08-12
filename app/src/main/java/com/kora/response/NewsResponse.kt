package com.kora.response

import com.google.gson.annotations.SerializedName
import com.kora.model.ArticleModel

data class NewsResponse(
    @SerializedName("articles")
    val articleModels: List<ArticleModel>
)