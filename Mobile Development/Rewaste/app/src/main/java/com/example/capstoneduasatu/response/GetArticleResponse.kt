package com.example.capstoneduasatu.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GetArticleResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: Any
)

@Parcelize
data class DataItem(

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("__v")
	val v: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("_id")
	val id: String
): Parcelable
