package com.example.capstoneduasatu.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.capstoneduasatu.helper.UserRepository
import com.example.capstoneduasatu.helper.model.UserModel
import com.example.capstoneduasatu.response.DataProfile
import com.example.capstoneduasatu.response.GetProfileResponse
import com.example.capstoneduasatu.retrofit.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _dataProfile = MutableLiveData<DataProfile>()
    val dataProfile: LiveData<DataProfile> = _dataProfile

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteToken()
        }
    }
    fun checkToken(): LiveData<UserModel> {
        return userRepository.getToken()
    }

    fun getUser(): LiveData<UserModel> {
        return userRepository.getUser().asLiveData()
    }

    fun getDataUser(token: String) {
        val client = ApiConfig.getApiService().getUserByUsername(token)
        client.enqueue(object : Callback<GetProfileResponse> {
            override fun onResponse(
                call: Call<GetProfileResponse>,
                response: Response<GetProfileResponse>
            ) {

                if (response.isSuccessful) {
                    val responseData = response.body()?.data
                    if (responseData != null) {
                        _dataProfile.value = responseData as DataProfile
                    } else {
                        Log.e(TAG, "Response data is null")
                    }
                }
                else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetProfileResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}