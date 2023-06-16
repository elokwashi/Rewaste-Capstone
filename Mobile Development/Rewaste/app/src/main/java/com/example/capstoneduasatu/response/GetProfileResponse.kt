package com.example.capstoneduasatu.response

import com.google.gson.annotations.SerializedName

data class GetProfileResponse(

	@field:SerializedName("data")
	val data: DataProfile,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: Any
)

data class DataProfile(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("__v")
	val v: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("_id")
	val id: String,

	@field:SerializedName("email")
	val email: String
)


