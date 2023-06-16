package com.example.capstoneduasatu.retrofit

import com.example.capstoneduasatu.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("api/v1/auth/signup")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @GET("api/v1/articles")
     suspend fun getArticle(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<GetArticleResponse>

    @GET("api/v1/articles")
    fun getSearch(
        @Header("Authorization") token: String,
        @Query("q") query: String
    ): Call<GetArticleResponse>

    @FormUrlEncoded
    @POST("api/v1/auth/signin")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @Multipart
    @POST("api/v1/articles")
    fun postArticle(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody
    ): Call<AddArticleResponse>


    @GET("api/v1/users/profile")
    fun getUserByUsername(
        @Header("Authorization") token : String,
    ): Call<GetProfileResponse>
}