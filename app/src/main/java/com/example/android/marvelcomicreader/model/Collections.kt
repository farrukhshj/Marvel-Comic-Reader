package com.example.android.marvelcomicreader.model

import com.google.gson.annotations.SerializedName


data class Collections (
	@SerializedName("resourceURI") val resourceURI : String,
	@SerializedName("name") val name : String
)