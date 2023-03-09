package com.ezatpanah.databinding_youtube.repository

import com.ezatpanah.databinding_youtube.api.ApiServices
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiServices: ApiServices) {
    suspend fun getTopHeadlineNews(country: String) =
        apiServices.getTopHeadlineNews(country)
}