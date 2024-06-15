package com.example.myapp.model

import com.google.gson.annotations.SerializedName

data class HewanResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("file")
	val file: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("namaLatin")
	val namaLatin: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
