package com.ezatpanah.databinding_youtube.api

import com.ezatpanah.databinding_youtube.response.ResponseTopHeadline
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("top-headlines")
    suspend fun getTopHeadlineNews(@Query("country") country: String): Response<ResponseTopHeadline>
}