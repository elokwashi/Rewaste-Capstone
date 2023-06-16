package com.example.capstoneduasatu.response

import com.google.gson.annotations.SerializedName

data class AddArticleResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)
