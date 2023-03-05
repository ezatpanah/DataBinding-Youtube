package com.ezatpanah.databinding_youtube.response

data class ResponseTopHeadline(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)