package com.example.capstoneduasatu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneduasatu.helper.UserRepository
import com.example.capstoneduasatu.helper.model.UserModel
import com.example.capstoneduasatu.response.AddArticleResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UploadViewModel(private val userRepository: UserRepository): ViewModel() {
    val uploadResponse: LiveData<AddArticleResponse> = userRepository.uploadResponse

    fun uploadArticle(token: String, file: MultipartBody.Part, name: RequestBody, description: RequestBody) {
        viewModelScope.launch {
            userRepository.uploadArticle(token, file,name,description)
        }
    }

    fun getSession(): LiveData<UserModel> {
        return userRepository.getToken()
    }
}