package com.ezatpanah.databinding_youtube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezatpanah.databinding_youtube.repository.ApiRepository
import com.ezatpanah.databinding_youtube.response.ResponseTopHeadline
import com.ezatpanah.databinding_youtube.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val _newsList: MutableLiveData<DataStatus<ResponseTopHeadline>> =MutableLiveData()
    val newsList: LiveData<DataStatus<ResponseTopHeadline>>
        get() = _newsList


    fun getTopHeadlineNews(country: String) = viewModelScope.launch {
        _newsList.value = DataStatus.loading()
        val response = repository.getTopHeadlineNews(country)
        _newsList.value = handleTopHeadlineNewsResponse(response)
    }

    private fun handleTopHeadlineNewsResponse(response: Response<ResponseTopHeadline>): DataStatus<ResponseTopHeadline> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return DataStatus.success(resultResponse)
            }
        }
        return DataStatus.error(response.message())
    }

}