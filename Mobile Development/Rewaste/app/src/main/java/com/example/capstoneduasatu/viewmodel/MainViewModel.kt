package com.example.capstoneduasatu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneduasatu.helper.UserRepository
import com.example.capstoneduasatu.helper.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository): ViewModel() {

    fun checkToken(): LiveData<UserModel> {
        return userRepository.getToken()
    }
}