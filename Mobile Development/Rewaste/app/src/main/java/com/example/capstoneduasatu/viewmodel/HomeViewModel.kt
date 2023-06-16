package com.example.capstoneduasatu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.capstoneduasatu.helper.UserRepository
import com.example.capstoneduasatu.response.DataItem

class HomeViewModel (
    private val userRepository: UserRepository
): ViewModel() {

    val getListArticle: LiveData<PagingData<DataItem>> =
        userRepository.getArticle().cachedIn(viewModelScope)

}