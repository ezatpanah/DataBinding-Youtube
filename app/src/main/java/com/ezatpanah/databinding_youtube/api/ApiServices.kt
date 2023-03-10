package com.ezatpanah.databinding_youtube.api

import com.ezatpanah.databinding_youtube.response.ResponseTopHeadline
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("top-headlines?country=us&page=1&pageSize=20")
    suspend fun getTopHeadlineNews(): Response<ResponseTopHeadline>
}