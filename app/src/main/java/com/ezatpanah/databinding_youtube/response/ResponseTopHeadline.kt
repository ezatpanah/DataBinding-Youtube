package com.ezatpanah.databinding_youtube.response

data class ResponseTopHeadline(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) {
    data class Article(
        val author: String,
        val content: String,
        val description: String,
        val publishedAt: String,
        val source: Source,
        val title: String,
        val url: String,
        val urlToImage: String
    ) {
        data class Source(
            val id: String,
            val name: String
        )
    }
}