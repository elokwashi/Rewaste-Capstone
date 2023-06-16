package com.example.capstoneduasatu.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneduasatu.helper.UserRepository
import com.example.capstoneduasatu.helper.model.UserModel
import com.example.capstoneduasatu.response.DataItem
import com.example.capstoneduasatu.response.GetArticleResponse
import com.example.capstoneduasatu.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InNonRecycleViewModel (private val userRepository: UserRepository): ViewModel() {
    private val _article = MutableLiveData<List<DataItem>>()
    val article: LiveData<List<DataItem>> = _article

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun checkToken(): LiveData<UserModel> {
        return userRepository.getToken()
    }
    init {
        findArticle(TOKEN,NAME)
    }

    fun findArticle(token:String, query :String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearch(token,query)
        client.enqueue(object : Callback<GetArticleResponse> {
            override fun onResponse(
                call: Call<GetArticleResponse>,
                response: Response<GetArticleResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _article.value = response.body()?.data
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<GetArticleResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object{
        private const val TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0N2M2MmZhOTI4NmFjOTNiZjhmNmY1ZCIsImlhdCI6MTY4NTg3MzkxOX0.8XhThqBnip18LCHMiaKIrk974QjQrZ-dYh5JhIC3jgI"
        private const val TAG = "InNonRecycleViewModel"
        private const val NAME = "NonRecy"
    }


}