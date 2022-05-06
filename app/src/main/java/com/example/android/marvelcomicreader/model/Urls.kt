package com.example.android.marvelcomicreader.model

import com.google.gson.annotations.SerializedName

data class Urls (

	@SerializedName("type") val type : String,
	@SerializedName("url") val url : String
)